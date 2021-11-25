package com.insect.controller;

import lombok.SneakyThrows;

import java.io.IOException;

public class Python {
    public static void main(String[] args) {
        Thread thread = new PythonThread();
        thread.run();
    }

}
class PythonThread extends Thread{
    @SneakyThrows
    public void run(){
        Runtime rt = Runtime.getRuntime();
        System.out.println("执行！");
        Process p = rt.exec("cmd.exe /c D: && cd D:\\faster-rcnn-pytorch-master && python predict.py");
    }
}
