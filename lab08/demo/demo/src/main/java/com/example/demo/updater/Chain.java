package com.example.demo.updater;

import com.example.demo.contract.MovieDto;
import com.example.demo.contract.OMDbDto;

public interface Chain {
    void setNextChain(Chain nextChain);

    void query(MovieDto movieDto, OMDbDto omDbDto);
}
