package com.neocampunism.utils

import org.mindrot.jbcrypt.BCrypt

fun String.hashPassword(): String = BCrypt.hashpw(this, BCrypt.gensalt())

fun String.verifyPassword(hashedPassword: String) = BCrypt.checkpw(this, hashedPassword)
