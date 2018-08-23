package com.dream.sell.utils;

import com.dream.sell.VO.ResultVO;

/**
 * @author zhumingli
 */
public class ResultVOUtil {
    /**
     * @param object
     * @return
     */
    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMeg("成功");
        resultVO.setData(object);
        return resultVO;

    }


    public static ResultVO success(){
        return null;
    }

    public static ResultVO error(Integer code, String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMeg(msg);

        return resultVO;

    }
}
