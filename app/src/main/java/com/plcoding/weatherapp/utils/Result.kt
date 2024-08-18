package com.plcoding.weatherapp.utils


sealed class Result<T>(var data: T? = null) {
    class Success<T>(data: T?): Result<T>(data)
    class Error<T>(message: Throwable): Result<T>(Nothing())
    object Loading: Result<Nothing>()
}
