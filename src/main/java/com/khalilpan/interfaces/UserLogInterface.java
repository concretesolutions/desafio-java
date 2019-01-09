package com.khalilpan.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khalilpan.beans.UserLog;

@Repository
public interface UserLogInterface extends JpaRepository<UserLog, Long> {

	
}
