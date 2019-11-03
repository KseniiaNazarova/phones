package com.phone.site.core.dao;

import java.util.List;

import com.phone.site.core.dao.entity.PhoneHistoryData;


/**
 * DAO for storing history of booked/returned phones
 */
public interface PhoneHistoryDAO
{
    /**
     * Gets history of booking/returning for a phone
     * @param phoneId
     * @return list of history records
     */
    List<PhoneHistoryData> getHistoryByPhoneId(final Long phoneId);


    /**
     * Adds return date for an existing record
     * @param phoneId
     * @return true, if a record is updated
     */
    boolean addReturnHistory(final Long phoneId);


    /**
     * Adds a new history record with a new book
     * @param phoneId
     * @param testerId id of a tester who has booked a phone
     * @return true, if a record is added
     */
    boolean addBookHistory(final Long phoneId, final Long testerId);
}
