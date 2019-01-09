package com.khalilpan.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khalilpan.beans.phone;

@Repository
public interface PhoneInterface extends JpaRepository<phone, Long> {

}
