package com.optimalCombinations.algo;

import java.util.ArrayList;


public class RoomSizeCombinations
{
	public static ArrayList<Integer> getSequence(int candidates[], int index[], int n)
	{
		ArrayList<Integer> genSeq = new ArrayList<Integer>();
		for (int i = 1; i <= n; i++)
		{
			genSeq.add(candidates[index[i]]);
		}
		return genSeq;
	}

	public static void calculate(int target, int sum, int roomSizes[], int size, int index[], int n, ArrayList<ArrayList<Integer>> sequences)
	{
		if (sum > target)
			return;
		if (sum == target)
		{
			sequences.add(getSequence(roomSizes, index, n));
		}

		for (int i = index[n]; i < size; i++)
		{
			index[n + 1] = i;
			calculate(target, sum + roomSizes[i], roomSizes, size, index, n + 1, sequences);
		}
	}

	public static ArrayList<ArrayList<Integer>> generateCombinations(int target, int roomSizes[]) 
	{
	  ArrayList<ArrayList<Integer>> sequence = new ArrayList<ArrayList<Integer>>();
	  int[] index = new int[10000];
	  index[0] = 0;
	  calculate(target, 0, roomSizes, roomSizes.length, index, 0, sequence);
	  System.out.println(sequence);
	  return sequence;
	}
	/*
	public static void main(String [] args)
	{
		int[] candidates = new int[]{3, 4, 5};
		generateCombinations(17, candidates);
	}*/
}
