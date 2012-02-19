/**
 * Copyright (C) 2005 the Lexi Project.
 *
 * This file is part of the Lexi document editor.
 *
 * Lexi is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 * 
 * Lexi is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with GNU Classpath; see the file COPYING.  If not, write to the
 * Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 *
 * Linking this library statically or dynamically with other modules is
 * making a combined work based on this library.  Thus, the terms and
 * conditions of the GNU General Public License cover the whole combination.
 */

package me.sheimi.support;

/**
 * This class provides rudimentary diagnostics / trace printing.
 */
public class Code {
	/** This flag enables traceprints via 'debug' calls */
	private static final boolean DEBUG = false;

	/** This flag enables traceprints via 'event' calls */
	private static final boolean DEBUG_EVENTS = false;

	public static void debug(String msg) {
		if (DEBUG) {
			System.err.println("debug: " + msg);
		}
	}

	public static void message(String msg) {
		System.err.println(msg);
	}

	public static void failed(String msg) {
		System.err.println("FAILED: " + msg);
		System.exit(1);
	}

	public static void failed(Throwable ex) {
		System.err.println("FAILED: " + ex);
		ex.printStackTrace();
		System.exit(1);

	}

	public static void event(Object event) {
		if (DEBUG_EVENTS) {
			System.err.println("event: " + event);
		}
	}
}
