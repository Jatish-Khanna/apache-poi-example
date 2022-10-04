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
			String[] inputs = { file1, file2 };

			for (String arg : inputs) {

				FileInputStream inputstream = new FileInputStream(arg);
				try (XMLSlideShow src = new XMLSlideShow(inputstream)) {

					for (XSLFSlide srcSlide : src.getSlides()) {

						// merging the contents
						ppt.createSlide(srcSlide.getSlideLayout());
					}
				}
			}

			String file3 = "combinedpresentation.pptx";

			// creating the file object
			FileOutputStream out = new FileOutputStream(file3);

			// saving the changes to a file
			ppt.write(out);
			System.out.println("Merging done successfully");
			out.close();
		}
	}
}
