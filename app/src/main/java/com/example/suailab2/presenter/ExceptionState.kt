package com.example.suailab2.presenter

sealed class ExceptionState{
    object Exception: ExceptionState()
    object NoException: ExceptionState()
}
