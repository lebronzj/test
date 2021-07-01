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

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;


/**
 * 字符串多种追加方式吞吐量基准测试
 *
 * @author MagicQ
 * @version 1.0
 * @date  10:54 2019/3/20
 *
 */
public class StringOpt {
	@Benchmark
	public void measureStringBufferApend() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < 10000; i++) {
			buffer.append("hello");
		}

	}

	@SuppressWarnings("unused")
	@Benchmark
	public static void measureStringAppend() {
		String targetString ="";
		for (int i = 0; i < 10000; i++) {
			 targetString += "hello";
		}

	}
	@SuppressWarnings("unused")
	@Benchmark
	public static void measureStringConsantAppend() {
		for (int i = 0; i < 10000; i++) {
			String targetString = "hello" + "world";
		}

	}

	@Benchmark
	public void measureStringBuilderAppend() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 10000; i++) {
			builder.append("hello");
		}

	}

	@Benchmark
	public void measureStringBuilderSyncrAppend() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 10000; i++) {
			synchronized (this) {
				builder.append("hello");
			}
		}

	}

	public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder().include(StringOpt.class.getSimpleName()).forks(1).warmupIterations(3)
				.warmupTime(TimeValue.valueOf("3")).measurementIterations(3).measurementTime(TimeValue.valueOf("3"))
				.build();

		new Runner(opt).run();
	}
}