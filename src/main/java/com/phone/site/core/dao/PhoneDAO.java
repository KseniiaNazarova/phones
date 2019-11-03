package com.phone.site.core.dao;

import java.util.List;
import java.util.Optional;

import com.phone.site.core.dao.entity.PhoneData;

/**
 * DAO to store common info about phones
 */
public interface PhoneDAO
{
    /**
     * Gets info about a phone
     * @param id phone id
     * @return optional info
     */
    Optional<PhoneData> getPhoneById(final Long id);


    /**
     * @return info for all phones
     */
    List<PhoneData> getAllPhones();


    /**
     * Marks the phone as not available
     * @param id phone id
     * @return true, if state is changed
     */
    boolean bookPhone(final Long id);


    /**
     * Marks the phone as available
     * @param id phone id
     * @return true, if state is changed
     */
    boolean returnPhone(final Long id);
}
