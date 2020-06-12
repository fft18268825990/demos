package com.rabbitmq;

public class SortedTest {
    public static void main(String[] args){
        int[] a = new int[]{10,9,13,8,3,4,7,12,11,1};
        //insertSort(a);
        sheetSort(a);
        for(int i : a){
            System.out.print(i+",");
        }
    }
    //直接插入排序
    public static void insertSort(int[] a){
        int length = a.length;
        int insertNum;
        for(int i = 1 ; i < length ; i++ ){
            insertNum = a[i];
            int j = i-1;
            while(j >= 0 && a[j] > insertNum){
                a[j+1] = a[j];
                j--;
            }
            a[j+1] = insertNum;
        }
    }
    //希尔排序
    public static void sheetSort(int[] a){
        int length = a.length;
        while (length!=0){
            length = length/2; //组数
            for(int i = 0 ; i < length ; i ++){
                for(int j = i + length ; j < a.length ; j += length){
                    int z = j - length;
                    int insertNum = a[j];
                    for(;z >= 0 && a[z] > insertNum ; z -= length){
                        a[z+length] = a[z];
                    }
                    a[z+length] = insertNum;
                }
            }
        }
    }
}

