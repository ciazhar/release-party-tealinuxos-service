package com.ciazhar.releasepartyservice.service;

import org.springframework.util.concurrent.ListenableFuture;

/**
 * Created by ciazhar on 16/12/17.
 * [ Documentatiion Here ]
 */
public interface ImageService {
    ListenableFuture<byte[]> generateQRCodeAsync(String text, int width, int heiht) throws Exception;
    void purgeCache();
}
