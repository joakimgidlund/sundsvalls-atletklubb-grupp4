package se.yrgo.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import se.yrgo.spring.data.CustomerDao;

@Transactional
@Service("customerService")
public class CustomerServiceProductionImpl implements CustomerService {

    CustomerDao customerDao;

    }
