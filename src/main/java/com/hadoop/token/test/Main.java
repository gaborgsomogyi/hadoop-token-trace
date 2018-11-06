package com.hadoop.token.test;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.security.Credentials;
import org.apache.hadoop.security.token.Token;
import org.apache.hadoop.security.token.delegation.AbstractDelegationTokenIdentifier;

import java.io.IOException;

public class Main {
  private static final Text tokenKind = new Text("testKind");

  private class TestDelegationTokenIdentifier extends AbstractDelegationTokenIdentifier {
    @Override
    public Text getKind() {
      return tokenKind;
    }
  }

  public static void main(String[] args) throws IOException {
    Configuration hadoopConf = new Configuration();
    FileSystem fs = FileSystem.get(hadoopConf);
    System.out.println("Hadoop FS obtained: " + fs.getUri());

    Credentials credentials = new Credentials();
    Token<TestDelegationTokenIdentifier> token = new Token<>(
        "testIdentifier".getBytes(),
        "testPassword".getBytes(),
        tokenKind,
        new Text("testService")
    );
    credentials.addToken(new Text("testAlias"), token);
    fs.addDelegationTokens("testRenewer", credentials);
    System.out.println("Tokens added: " + credentials.getAllTokens());
  }
}
