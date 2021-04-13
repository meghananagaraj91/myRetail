package com.trgt.mrl.myRetail.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.trgt.mrl.myRetail.entiry.Role;

public interface RoleRepository extends MongoRepository<Role, String> {

}
