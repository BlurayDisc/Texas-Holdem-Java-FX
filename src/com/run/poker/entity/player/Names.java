package com.run.poker.entity.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * Name Templates
 * @author RuN
 *
 */
public enum Names {
	
	Chris,
	Michael, 
	Brendan, 
	Yong, 
	Kevin, 
	Callum, 
	Run,
	Kosta,
	Adam,
	Xandar,
	Gavin,
	Luke,
	Davi;
	
	public static List<Names> list = 
			new ArrayList<>(Arrays.asList(Names.values()));
	
	public static void reset() {
		Names.list = new ArrayList<>(Arrays.asList(Names.values()));
	}
}
