package com.wm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 BACKGROUND
 Sometimes items cannot be shipped to certain zip codes, and the rules for these restrictions are stored as a series of ranges of 5 digit codes. For example if the ranges are:

 [94133,94133] [94200,94299] [94600,94699]

 Then the item can be shipped to zip code 94199, 94300, and 65532, but cannot be shipped to 94133, 94650, 94230, 94600, or 94299.

 Any item might be restricted based on multiple sets of these ranges obtained from multiple sources.

 PROBLEM
 Given a collection of 5-digit ZIP code ranges (each range includes both their upper and lower bounds), provide an algorithm that produces the minimum number of ranges required to represent the same restrictions as the input.

 NOTES
 - The ranges above are just examples, your implementation should work for any set of arbitrary ranges
 - Ranges may be provided in arbitrary order
 - Ranges may or may not overlap
 - Your solution will be evaluated on the correctness and the approach taken, and adherence to coding standards and best practices

 EXAMPLES:
 If the input = [94133,94133] [94200,94299] [94600,94699]
 Then the output should be = [94133,94133] [94200,94299] [94600,94699]

 If the input = [94133,94133] [94200,94299] [94226,94399]
 Then the output should be = [94133,94133] [94200,94399]

 Evaluation Guidelines:
 Your work will be evaluated against the following criteria:
 - Successful implementation
 - Efficiency of the implementation
 - Design choices and overall code organization
 - Code quality and best practices
 **/
public class ZipCodeRange {

    public static class ZipCode implements Comparable<ZipCode>{
        private int start;
        private int end;

        public ZipCode(int currentStart, int currentEnd) {
            this.start = currentStart;
            this.end = currentEnd;
        }

        @Override
        public int compareTo(ZipCode that) {
            return Integer.valueOf(this.start).compareTo(Integer.valueOf(that.start));
        }

        @Override
        public String toString() {
            return "ZipCode{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }


    public static void main(String[] arg) {

        List<ZipCode> input1 = new ArrayList<>();
        input1.add(new ZipCode(94133,94133));
        input1.add(new ZipCode(94200,94299));
        input1.add(new ZipCode(94600,94699));

        System.out.println("input >> "+input1);
        System.out.println("output1 >> "+getRanges(input1));
        System.out.println("=========================");


        List<ZipCode> input2 = new ArrayList<>();
        input2.add(new ZipCode(94133,94133));
        input2.add(new ZipCode(94200,94299));
        input2.add(new ZipCode(94226,94399));

        System.out.println("input2 >> "+input2);
        System.out.println("output2 >> "+getRanges(input2));
        System.out.println("=========================");



        List<ZipCode> input3 = new ArrayList<>();

        input3.add(new ZipCode(94133,94133));
        System.out.println("input3 >> "+input3);
        System.out.println("output3 >> "+getRanges(input3));
        System.out.println("=========================");


        List<ZipCode> input4 = new ArrayList<>();

        input4.add(new ZipCode(94133,94133));
        input4.add(new ZipCode(94133,94135));

        System.out.println("input4 >> "+input4);
        System.out.println("input4 >> "+getRanges(input4));
        System.out.println("=========================");


        List<ZipCode> input5 = new ArrayList<>();

        input5.add(new ZipCode(94133,94133));
        input5.add(new ZipCode(94133,94140));
        input5.add(new ZipCode(94133,94135));


        System.out.println("input5 >> "+input5);
        System.out.println("input5 >> "+getRanges(input5));
        System.out.println("=========================");


        List<ZipCode> input6 = new ArrayList<>();

        input6.add(new ZipCode(94120,94120));
        input6.add(new ZipCode(94121,94122));
        input6.add(new ZipCode(94115,94119));


        System.out.println("input6 >> "+input6);
        System.out.println("input6 >> "+getRanges(input6));
        System.out.println("=========================");

    }

    private static List<ZipCode> getRanges(List<ZipCode> input) {

        Collections.sort(input);
        List<ZipCode> retList = new ArrayList<>();
        if(input == null || input.isEmpty() || input.size() == 1) {
            return input;
        }

        int currentStart = input.get(0).start;
        int currentEnd = input.get(0).end;

        for(int i=1; i<input.size(); i++) {

            if(input.get(i).end < input.get(i).start) {
                System.out.println("wrong data range. end is less than start ");
                System.out.println(input.get(i));
                continue;
            }

            if(input.get(i).start <= currentEnd) {
                currentEnd = Math.max(input.get(i).end, currentEnd);
            } else {
                retList.add(new ZipCode(currentStart, currentEnd));
                currentStart = input.get(i).start;
                currentEnd = input.get(i).end;
            }
        }

        retList.add(new ZipCode(currentStart, currentEnd));



        return retList;
    }


}
