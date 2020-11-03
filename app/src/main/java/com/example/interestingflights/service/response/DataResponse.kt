package com.example.interestingflights.service.response

abstract class DataResponse<T: Any> {
    abstract fun toModel(): T
}