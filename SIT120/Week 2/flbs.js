/*!
 * Start Bootstrap - Freelancer v4.0.0-beta (https://mango.pdf.zone) (yes I totally did borrow this from "Alex")
 * Copyright 2013-2020 Start Bootstrap
 * Licensed under MIT (https://github.com/BlackrockDigital/startbootstrap-freelancer/blob/master/LICENSE)
 */
! function(e) {
    "use strict";
    "" != document.referrer && document.referrer !== document.location.href && (e("div.useragent a").text(document.referrer), e("div.useragent").show()), e("#maxmorimode").change(function() {
        this.checked ? (window.stopEmoji = !1, emojiCursor()) : (window.stopEmoji = !0, emojiCursor())
    });
}(jQuery);