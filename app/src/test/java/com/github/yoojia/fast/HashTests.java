package com.github.yoojia.fast;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Hash testcases
 *
 * @author  Yoojia.Chen@gmail.com
 * @version 2015-04-09
 * @since   1.0
 */
@RunWith(RobolectricTestRunner.class)
public class HashTests {

    @Test
    public void testSHA1(){
        String hash = HashEncryption.encrypt("yoojiachen", "SHA-1");
        assertThat(hash, is("3f3a622aec7f870281d1570f651b28ea41d02eab"));
    }

    @Test
    public void testSHA256(){
        String hash = HashEncryption.encrypt("yoojiachen", "SHA-256");
        assertThat(hash, is("f100a022ff6b27983c8360aa17994eda060a4494e7003132a8ceaa2d79681877"));
    }

    @Test
    public void testSHA512(){
        String hash = HashEncryption.encrypt("yoojiachen", "SHA-512");
        assertThat(hash, is("a29cdb37017c63fe16d5a1a2ed5aafede73f01b4b648bd6177e80f9d291ec0bd35546609b3de24fb61e2b694fb88eaee8215ec7a99cf34660bf496a96ce06e8f"));
    }

    @Test
    public void testMD5(){
        String hash = HashEncryption.encrypt("yoojiachen", "MD5");
        assertThat(hash, is("cbab6ba79b9b08f9cc16e1de3990c308"));
    }
}
