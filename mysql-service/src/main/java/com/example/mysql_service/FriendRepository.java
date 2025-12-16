package com.example.mysql_service;

import org.springframework.data.repository.CrudRepository;
import com.example.mysql_service.Friend;

public interface FriendRepository extends CrudRepository<Friend, Integer> {
}
