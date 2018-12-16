package com.cmautomation.spring.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cmautomation.spring.dao.ApplicationDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@PropertySource({"classpath:application-persistence.properties","classpath:application-security.properties"})
@ContextConfiguration
public class ApplicationServiceImplTest {
	
	
	@Mock
	ApplicationDAO applicationDAO;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test() {
		ApplicationServiceImpl applicationService=new ApplicationServiceImpl();
		applicationService.saveApplication(null);
	}

}
