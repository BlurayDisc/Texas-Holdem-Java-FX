package com.run.poker.player;

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
	
	private static final List<Names> NAMES = 
			new ArrayList<>(Arrays.asList(Names.values()));
	
	public static List<Names> list() {
		return NAMES;
	}
}
