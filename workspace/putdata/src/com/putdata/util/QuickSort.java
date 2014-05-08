package com.putdata.util;



public class QuickSort {
	
	private static int adjust(long[] arr,int low,int height){
		long pivote=arr[low];
		while(low<height){
			while(height>low&&arr[height]>=pivote){
				height--;
			}
			arr[low]=arr[height];
			while(height>low&&arr[low]<=pivote){
				low++;
			}
			arr[height]=arr[low];
		}
		arr[low]=pivote;
		return low;
	}
	
	public static void quitesort(long[] arr,int low,int height){
		if(low<height){
			int povitePosition =adjust(arr,low,height);
			quitesort(arr,low,povitePosition-1);
			quitesort(arr,povitePosition+1,height);
		}
	}
	
}
