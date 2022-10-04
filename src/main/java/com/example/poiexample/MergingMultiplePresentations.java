package com.example.poiexample;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class MergingMultiplePresentations {

	@EventListener(ApplicationReadyEvent.class)
	public void merge() throws IOException {

		// creating empty presentation
		try (XMLSlideShow ppt = new XMLSlideShow()) {

			// taking the two presentations that are to be merged
			String file1 = "presentation1.pptx";
			String file2 = "presentation2.pptx";
