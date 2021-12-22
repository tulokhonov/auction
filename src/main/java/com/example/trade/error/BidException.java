package com.example.trade.error;

public class BidException extends RuntimeException
{
    public BidException(String message) {
        super(message);
    }

    public BidException(String message, Throwable cause) {
        super(message, cause);
    }
}
