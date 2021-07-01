/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.test.jmh.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.*;

/**
 * 数组多种迭代方式吞吐量基准测试
 *
 * @author MagicQ
 * @version 1.0
 * @date 10:54 2019/3/20
 */
public class CollectorOpt {
	@State(Scope.Thread)
	public static class ArrayState {
		public String[] arr = new String[10000];
		public ArrayList<String> arrayList = new ArrayList<String>(10000);
		public LinkedList<String> linkedList = new LinkedList<String>();

		@Setup(Level.Trial)
		public void doSetup() {
			System.out.println("Do Setup");

			for (int i = 0; i < 10000; i++) {
				arr[i] = i + "";
				arrayList.add(i + "");
				linkedList.add(i + "");
			}
		}

		@TearDown(Level.Trial)
		public void doTearDown() {
			arr = null;
			arrayList.clear();
			linkedList.clear();
			System.out.println("Do TearDown");
		}

	}

	@Benchmark
	public void measureArrayForLoop(ArrayState state) {
		for (int i = 0; i < state.arr.length; i++) {
		}

	}

	@SuppressWarnings("unused")
	@Benchmark
	public static void measureArrayForIncrement(ArrayState state) {
		for (String item : state.arr) {
		}

	}

	@Benchmark
	public void measureArrayToArrayListForEach(ArrayState state) {
		List<String> list = Arrays.asList(state.arr);
		// list.add(e)
		list.forEach((item) -> {
		});

	}

	@Benchmark
	public void measureArrayListLoop(ArrayState state) {
		for (int i = 0; i < state.arrayList.size(); i++) {
		}

	}

	@SuppressWarnings("unused")
	@Benchmark
	public void measureArrayListIncreament(ArrayState state) {
		for (String item : state.arrayList) {

		}

	}

	@Benchmark
	public void measureArrayListForEach(ArrayState state) {
		state.arrayList.forEach((item) -> {
		});

	}

	@Benchmark
	public void measureArrayListListIterator(ArrayState state) {
		Iterator<String> iter = state.arrayList.iterator();
		while (iter.hasNext()) {
			iter.next();
		}

	}

	@Benchmark
	public void measureLinkedListLoop(ArrayState state) {
		for (int i = 0; i < state.linkedList.size(); i++) {
		}

	}

	@SuppressWarnings("unused")
	@Benchmark
	public void measureLinkedListIncreament(ArrayState state) {
		for (String item : state.linkedList) {
		}

	}

	@Benchmark
	public void measureLinkedListForEach(ArrayState state) {
		state.linkedList.forEach((item) -> {
		});
	}

	@Benchmark
	public void measureLinkedListIterator(ArrayState state) {
		Iterator<String> iter = state.linkedList.iterator();
		while (iter.hasNext()) {
			iter.next();
		}

	}

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder().include(CollectorOpt.class.getSimpleName()).forks(1).warmupIterations(3)
				.warmupTime(TimeValue.seconds(1)).measurementIterations(3).measurementTime(TimeValue.seconds(1))
				.build();

		new Runner(opt).run();
	}
}