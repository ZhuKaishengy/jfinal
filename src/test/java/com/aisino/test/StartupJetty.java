package com.aisino.test;

import com.jfinal.core.JFinal;

public class StartupJetty {

	public static void main(String[] args) {
		JFinal.start("src/main/webapp", 8080, "/");
		
	}
	
}
