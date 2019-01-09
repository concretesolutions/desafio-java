package com.khalilpan.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khalilpan.beans.User;

@Repository
public interface UserInterface extends JpaRepository<User, Long> {

}
