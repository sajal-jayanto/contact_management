package com.cramstack.contact_management.Repositorys;

import com.cramstack.contact_management.Models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact , Integer> {

}
