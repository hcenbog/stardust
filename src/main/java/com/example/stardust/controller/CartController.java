package com.example.stardust.controller;

import com.example.stardust.Utile.JsonResult;
import com.example.stardust.service.ICartService;
import com.example.stardust.service.ex.AccessDeniedException;
import com.example.stardust.service.ex.CartNotFoundException;
import com.example.stardust.service.ex.DeleteException;
import com.example.stardust.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author AlHeae
 * @Description
 * @date 2023/4/13 3:49
 */
@RestController
@ResponseBody
@RequestMapping("carts")
public class CartController extends BaseController {
    @Autowired
    private ICartService cartService;

    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid, Integer amount, HttpSession session) {

        cartService.addToCart(getuidFromSession(session), pid,
                amount, getUsernameFromSession(session));
        // 返回成功
        return new JsonResult<Void>(OK);
    }

    @GetMapping({"", "/"})
    public JsonResult<List<CartVO>> getVOByUid(HttpSession session) {
// 从Session中获取uid
        Integer uid = getuidFromSession(session);
// 调用业务对象执行查询数据
        List<CartVO> data = cartService.getVOByUid(uid);
// 返回成功与数据
        return new JsonResult<List<CartVO>>(OK, data);
    }
//增加数量
    @RequestMapping("{cid}/num/add")
    public JsonResult<Integer> addNum(@PathVariable("cid") Integer cid, HttpSession
            session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        Integer data = cartService.addNum(cid, uid, username);
        return new JsonResult<Integer>(OK, data);
    }
//删除商品
    @DeleteMapping("/{cid}")
    public ResponseEntity<String> deleteCartItem(@PathVariable("cid") Integer cid,
                                                 HttpSession session) {
        Integer uid = getuidFromSession(session);
        String username = getUsernameFromSession(session);
        try {
            cartService.deleteByCid(cid, uid, username);
            return ResponseEntity.ok("Cart item deleted successfully");
        } catch (CartNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (DeleteException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("list")
    public JsonResult<List<CartVO>> getVOByCids(Integer[] cids, HttpSession session) {
        Integer uid = getuidFromSession(session);
        List<CartVO> data = cartService.getVOByCids(uid, cids);
        return new JsonResult<>(OK, data);
    }

}
