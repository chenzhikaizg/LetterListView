package com.example.letterlist;

import java.util.List;

/**
 * Created by ${chenzhikai} on 2018/4/24.
 */

public class ContactBean {
    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }


    private String letter;

    public List<ContactBeanDetail> getContactBeanDetail() {
        return contactBeanDetail;
    }

    public void setContactBeanDetail(List<ContactBeanDetail> contactBeanDetail) {
        this.contactBeanDetail = contactBeanDetail;
    }

    private List<ContactBeanDetail> contactBeanDetail;
   public  static class ContactBeanDetail{
        public String getContactPerson() {
            return contactPerson;
        }

        public void setContactPerson(String contactPerson) {
            this.contactPerson = contactPerson;
        }

        private String contactPerson;
    }
}
