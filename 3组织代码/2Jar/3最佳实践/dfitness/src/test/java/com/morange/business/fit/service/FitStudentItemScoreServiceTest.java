package com.morange.business.fit.service;

import common.SpringBaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FitStudentItemScoreServiceTest extends SpringBaseTest {

    @Autowired
    private FitStudentItemScoreService fitStudentItemScoreService;

    @Test
    public void testgetStatisticsItemCol() {
         fitStudentItemScoreService.getStatisticsItemCol("test1");
    }

    @Test
    public void testgetStatisticsItem() {
        fitStudentItemScoreService.getStatisticsItem("test1");

    }
    @Test
    public void testgetStatisticsStudent() {
        fitStudentItemScoreService.getStatisticsStudent("test1");

    }
}