package smsw.maah.data.entity

class User {
    var uid: String? = null
    var nickname: String? = null
    var emailId: String? = null
    var password: String? = null

    constructor()

    constructor(uid: String, nickname: String, email: String, password: String) {
        this.uid = uid
        this.nickname = nickname
        this.emailId = email
        this.password = password
    }
}
