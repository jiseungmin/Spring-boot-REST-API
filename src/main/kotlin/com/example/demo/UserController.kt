package com.example.demo

import jakarta.annotation.PostConstruct
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.web.bind.annotation.*

@RestController
@EnableAutoConfiguration(exclude = [DataSourceAutoConfiguration::class])
class UserController {
    private val userMap: MutableMap<String, UserInfo> = mutableMapOf()

    @PostConstruct
    fun init() {
        userMap["1"] = UserInfo("1", "호랑이", "321321-32", "광주")
        userMap["2"] = UserInfo("2", "용", "3214=321", "서울")
        userMap["3"] = UserInfo("3", "쥐", "6547-1241", "판교")
    }

    // GET
    @GetMapping(path = ["/user/{id}"])
    fun getUserInfo(@PathVariable("id") id: String) = userMap[id]

    @GetMapping(path = ["user/all"])
    fun getUserInfoAll() = ArrayList<UserInfo>(userMap.values)

    //POST
    @PostMapping(path = ["/user/{id}"])
    fun postUserInfo(@PathVariable("id") id: String, @RequestParam("name") name: String, @RequestParam("phone") phone: String, @RequestParam("address") address: String) {

        val userInfo = userMap[id]
        userInfo?.name = name
        userInfo?.phone = phone
        userInfo?.address = address
    }

    //PUT
    @PutMapping(path = ["/user/{id}"])
    fun putUserInfo(@PathVariable("id") id: String, @RequestParam("name") name: String, @RequestParam("phone") phone: String, @RequestParam("address") address: String) {

        val userInfo = UserInfo(id, name, phone, address)
        userMap[id] = userInfo
    }

    //DELETE
    @DeleteMapping(path = ["/user/{id}"])
    fun deleteUserInfo(@PathVariable("id") id: String) = userMap.remove(id)

}