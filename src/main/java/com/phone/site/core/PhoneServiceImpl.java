package com.phone.site.core;

import java.util.List;
import java.util.stream.Collectors;

import com.phone.site.api.Model;
import com.phone.site.api.Phone;
import com.phone.site.api.PhoneHistory;
import com.phone.site.api.PhoneId;
import com.phone.site.api.TechDetails;
import com.phone.site.api.TesterId;
import com.phone.site.core.dao.PhoneDAO;
import com.phone.site.core.dao.PhoneHistoryDAO;
import com.phone.site.core.dao.entity.PhoneData;
import com.phone.site.core.dao.entity.PhoneHistoryData;
import com.phone.site.core.excpetions.BookPhoneException;
import com.phone.site.core.excpetions.FonoapiRuntimeException;
import com.phone.site.core.excpetions.PhoneNotFoundException;
import com.phone.site.core.excpetions.ReturnPhoneException;
import com.phone.site.core.integration.TechDetailsData;
import com.phone.site.core.integration.cache.TechDetailsCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PhoneServiceImpl implements PhoneService
{
    private static final Logger logger = LoggerFactory.getLogger(PhoneServiceImpl.class);

    private final PhoneDAO phoneDAO;

    private final PhoneHistoryDAO phoneHistoryDAO;

    private final TechDetailsCache techDetailsCache;


    public PhoneServiceImpl(final PhoneDAO phoneDAO,
                            final PhoneHistoryDAO phoneHistoryDAO,
                            final TechDetailsCache techDetailsCache)
    {
        this.phoneDAO = phoneDAO;
        this.phoneHistoryDAO = phoneHistoryDAO;
        this.techDetailsCache = techDetailsCache;
    }


    @Override
    public Phone getPhoneDetails(final PhoneId phoneId)
    {
        logger.debug("get phone details: phoneId={}", phoneId);

        final Phone phone = phoneDAO.getPhoneById(phoneId.asLong())
                .map(this::getPhoneWithDetails)
                .orElseThrow(() -> new PhoneNotFoundException(phoneId.asLong()));

        logger.info("get phone details result: phoneId={}, result={}", phoneId, phone);
        return phone;
    }


    @Override
    public List<Phone> getPhones()
    {
        logger.debug("get all phones details");

        final List<Phone> result = phoneDAO.getAllPhones().stream()
                .map(this::getPhoneWithDetails)
                .collect(Collectors.toList());

        logger.info("get all phones details result: result={}", result);
        return result;
    }


    @Override
    @Transactional
    public void bookPhone(final PhoneId phoneId, final TesterId testerId)
    {
        logger.debug("Book phone: phoneId={}, testerId={}", phoneId, testerId);

        boolean isUnavailable = phoneDAO.bookPhone(phoneId.asLong());
        boolean isBooked = phoneHistoryDAO.addBookHistory(phoneId.asLong(), testerId.asLong());

        if (!(isUnavailable && isBooked))
        {
            throw new BookPhoneException(phoneId.asLong(), testerId.asLong());
        }

        logger.info("Phone is booked: phoneId={}, testerId={}", phoneId, testerId);
    }


    @Override
    public void returnPhone(final PhoneId phoneId)
    {
        logger.debug("Return phone: phoneId={}", phoneId);

        boolean isAvailable = phoneDAO.returnPhone(phoneId.asLong());
        boolean isReturned = phoneHistoryDAO.addReturnHistory(phoneId.asLong());

        if (!(isAvailable && isReturned))
        {
            throw new ReturnPhoneException(phoneId.asLong());
        }

        logger.info("Phone is returned: phoneId={}", phoneId);
    }


    @Override
    public List<PhoneHistory> getPhoneHistory(final PhoneId phoneId)
    {
        logger.debug("get all history for a phone: phoneId={}", phoneId);

        final List<PhoneHistory> result = phoneHistoryDAO.getHistoryByPhoneId(phoneId.asLong())
                .stream()
                .map(this::mapToHistory)
                .collect(Collectors.toList());

        logger.info("get all history for a phone: phoneId={}, result={}", phoneId, result);
        return result;
    }


    private Phone getPhoneWithDetails(final PhoneData phoneData)
    {
        return techDetailsCache.get(phoneData.getModelData())
                .map(details -> mapToPhone(phoneData, details))
                .orElseThrow(FonoapiRuntimeException::new);
    }


    private Phone mapToPhone(final PhoneData phone, final TechDetailsData techDetails)
    {
        return Phone.builder()
                .withId(phone.getId())
                .withModel(Model.builder()
                                   .withModel(phone.getModelData().getModel())
                                   .withBrand(phone.getModelData().getBrand())
                                   .build())
                .isAvailable(phone.isAvailable())
                .withTechDetails(TechDetails.builder()
                                         .withBands2g(techDetails.getBands2g())
                                         .withBands3g(techDetails.getBands3g())
                                         .withBands4g(techDetails.getBands4g())
                                         .withTechnology(techDetails.getTechnology())
                                         .build())
                .build();
    }


    private PhoneHistory mapToHistory(final PhoneHistoryData data)
    {
        return PhoneHistory.builder()
                .withPhoneId(data.getPhoneId())
                .withModel(Model.builder()
                                   .withBrand(data.getModelData().getBrand())
                                   .withModel(data.getModelData().getModel())
                                   .build())
                .withBookedOn(data.getBookedOn())
                .withReturnedOn(data.getReturnedOn())
                .build();
    }
}
