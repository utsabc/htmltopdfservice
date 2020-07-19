package com.service.resource.internalimpl;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import org.apache.commons.codec.binary.Base64;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.service.resource.internal.ICoreService;
import com.service.resource.pojos.HealthMetric;
import com.service.resource.pojos.InputEntity;
import com.service.resource.pojos.OutputEntity;
import com.service.resource.util.CommonUtil;

@Service
public class CoreService implements ICoreService {

	private final Logger logger = LoggerFactory.getLogger(CoreService.class);
	
	@Autowired
	CommonUtil commonUtil;

	@Override
	public OutputEntity process(InputEntity input) throws Exception {
		OutputEntity response = new OutputEntity();
		org.w3c.dom.Document doc = htmlParseW3C(input.getHtml());
		byte[] output = parseToPdf(doc,input.getFilename());
		response.setFilename(input.getFilename().concat(".pdf"));
		response.setContent(Base64.encodeBase64String(output));
		FileOutputStream fos = new FileOutputStream("C:\\Users\\Retro Ronin\\Desktop\\Grad Courses\\"+response.getFilename()+"test"+".pdf");
		fos.write(output);
		fos.close();
		return response;
	}
	private org.w3c.dom.Document htmlParseW3C(String html){

		Document doc = null;
		doc = Jsoup.parse(html);
		return new W3CDom().fromJsoup(doc);
	}

	private byte[] parseToPdf(org.w3c.dom.Document input,String filename) throws Exception { 
		//"C:\\Users\\Retro Ronin\\Desktop\\Grad Courses\\"+filename+".pdf"
		try (OutputStream bytearrayStream = new ByteArrayOutputStream()){
			byte[] pdfByteArray = null;
			logger.info((String) commonUtil.getInfoMap().get("Host"));
			PdfRendererBuilder builder = new PdfRendererBuilder();
			builder.useFastMode();
			builder.withW3cDocument(input, "http://"+(String) commonUtil.getInfoMap().get("Host"));
			builder.toStream(bytearrayStream);
			builder.run();
			pdfByteArray = ((ByteArrayOutputStream)bytearrayStream).toByteArray();
			bytearrayStream.close();
			return pdfByteArray;
		}

	}
	@Override
	public HealthMetric heartBeat() {
		RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
		HealthMetric healthMetric = new HealthMetric(runtimeMXBean.getUptime());
		return healthMetric;
	}
}
