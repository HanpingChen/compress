package com.chen.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.chen.analyse.AnalyseQuality;
import com.chen.compress.DNASqCompressor;
import com.chen.compress.RunLehgthCodeCompressor;
import com.chen.compress.TitleCompressor;
import com.chen.fileoperator.FastqFileReader;
import com.chen.fileoperator.FastqFileWriter;
import com.chen.fileoperator.onReadListener;
import com.chen.model.Record;
import com.chen.util.MethodUtil;

/**
 * 调用压缩算法的入口类
 * @author chen
 *
 */
public class CCompressor implements onReadListener{

	private String inputpath;
	private String outputpath;
	private File file;
	private AnalyseQuality analyseQuality = new AnalyseQuality();
	private RunLehgthCodeCompressor codeCompressor = new RunLehgthCodeCompressor();
	private DNASqCompressor dnaSqCompressor = DNASqCompressor.getInstance();
	private FastqFileReader reader;
	
	//写入标题行临时文件
	private FastqFileWriter titleWriter = new FastqFileWriter(new File("title.temp"));
	//写入DNA行临时文件
	private FastqFileWriter dnaWriter = new FastqFileWriter(new File("dna.temp"));
	//写入质量行临时文件
	private FastqFileWriter qualityWriter = new FastqFileWriter(new File("quality.temp"));
	public CCompressor(String path,String outputpath) {
		// TODO Auto-generated constructor stub
		this.inputpath = path;
		this.outputpath = outputpath;
		file = new File(path);
	}
	public void compress(){
		reader = new FastqFileReader(this,inputpath);
		reader.read();
	}
	@Override
	public void onProgress(Record record, long currentLength) {
		// TODO Auto-generated method stub
		String title = record.getTitle();
		String dna = record.getDNBSequence();
		String thirdStr = record.getThirdStr();
		String quality = record.getQualityScore();
		
		TitleCompressor titileCompressor = TitleCompressor.getInstance(title);		
		
		List<Integer> dnaList = new ArrayList<>();
		
		dna = MethodUtil.getBitStr(dna);
		dnaSqCompressor.setDna(dna);
		dnaList = dnaSqCompressor.getCompressList();
		
		titileCompressor = TitleCompressor.getInstance(title);
		List<Integer> compressInt = titileCompressor.getCompressInt();
		//将压缩的标题行写入标题行的临时文件
		titleWriter.write(compressInt);
		
		//将dna压缩之后写入DNA临时文件
		dnaWriter.write(dnaList);
		
		codeCompressor.setQuality(quality);
		String codeStr = codeCompressor.getCodeStr();
		//将压缩之后的质量行写入质量行临时文件
		qualityWriter.write(codeStr);
	}
	@Override
	public void onFail(String error) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onComplete() {
		// TODO Auto-generated method stub
		LzCompress lzCompress = new LzCompress(outputpath);
		lzCompress.compress();
		
	}
	@Override
	public void onBegin(String title) {
		// TODO Auto-generated method stub
		
	}
}
