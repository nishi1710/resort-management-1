package com.example.resortmanagement.command;

import java.util.Map;

public interface OrderCommand {
    void execute(Map<String, String> params);
}
