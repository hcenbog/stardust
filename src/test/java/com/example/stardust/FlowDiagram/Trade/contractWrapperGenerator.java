package com.example.stardust.FlowDiagram.Trade;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.web3j.codegen.SolidityFunctionWrapperGenerator;

/**
 * @author AlHeae
 * @Description
 * @date 2023/4/30 1:50
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class contractWrapperGenerator {
   //bin 地址
    String binFile = "D:\\zhuomian\\stardust\\src\\test\\java\\com\\example\\stardust\\FlowDiagram\\Trade\\SteelPlateContract.bin";
    //abi 地址
    String abiFile = "D:\\zhuomian\\stardust\\src\\test\\java\\com\\example\\stardust\\FlowDiagram\\Trade\\SteelPlateContract.abi";
   //文件存放地址
    String outputDir = "D:\\zhuomian\\sol";
   //文件存储的包位置
    String packageName = "com";
//将文件转为java.html可运行的文件
    @Test
    public void or() {
        SolidityFunctionWrapperGenerator.main(new String[]{"-b", binFile, "-a", abiFile, "-o", outputDir, "-p", packageName});
    }
}

