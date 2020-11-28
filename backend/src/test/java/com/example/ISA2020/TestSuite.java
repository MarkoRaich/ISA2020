package com.example.ISA2020;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.example.ISA2020.controller.HospitalControllerTest;
import com.example.ISA2020.repository.HospitalRepositoryUnitTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({HospitalRepositoryUnitTests.class, HospitalControllerTest.class})
public class TestSuite {
}
