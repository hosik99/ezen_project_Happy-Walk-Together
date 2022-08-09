var onTypeTimeout;

function validateTime(value) {
    var comp = value.split(":");
    if (comp.length == 3) {
        var h = parseInt(comp[0], 10);
        var m = parseInt(comp[1], 10);
        var s = parseInt(comp[2], 10);
        return h <= 23 && h >= 0 && m <= 59 && m >= 0 && s <= 59 && s >= 0;
    }
    if (comp.length == 2) {
        var h = parseInt(comp[0], 10);
        var m = parseInt(comp[1], 10);
        return h <= 23 && h >= 0 && m <= 59 && m >= 0;
    }
    return false;
}

const getAge = function (birthDate) {
    return Math.floor((new Date() - parseDate(birthDate)) / 3.15576e+10);
};

const validateNotChar = function (value, chars) {
    chars = chars.split("");
    for (var i = 0; i < chars.length; i++) {
        if (value.indexOf(chars[i]) >= 0) {
            return false;
        }
    }
    return true;
};
const validateAnyChar = function (value, chars) {
    chars = chars.split("");
    var v = [];
    for (var i = 0; i < chars.length; i++) {
        if (value.indexOf(chars[i]) >= 0) {
            v.push(true);
        }
    }
    return v.indexOf(true) >= 0;
};

const validateAllChar = function (value, chars) {
    chars = chars.split("");
    var v = [];
    for (var i = 0; i < chars.length; i++) {
        if (value.indexOf(chars[i]) >= 0) {
            v.push(true);
        } else { v.push(false); }
    }
    return v.indexOf(false) < 0;
};

function validDate(value) {
    var datenumber = parseDate(value);
    return datenumber != null && !isNaN(datenumber);
}

function parseDate(value) {
    var dt = 0;
    var d = 0;
    var m = 0;
    var y = 0;
    var comp = value.split(" ")[0].split("/");
    if (comp.length == 3) {
        comp[2] = comp[2].length == 2 ? new Date().getFullYear().toString().substring(0, 2) + comp[2] : comp[2];
        d = parseInt(comp[0], 10);
        m = parseInt(comp[1], 10) - 1;
        y = parseInt(comp[2], 10);
    }
    if (comp.length == 2) {
        comp[1] = comp[1].length == 2 ? new Date().getFullYear().toString().substring(0, 2) + comp[1] : comp[1];
        d = 1
        m = parseInt(comp[0], 10) - 1;
        y = parseInt(comp[1], 10);
    }
    dt = new Date(y, m, d);
    var lastday = new Date(y, m + 1, 0);
    if (m > 11 || m < 0) { return null }
    if (d > lastday.getDate() || d < 1) { return null }
    if (dt > 0) { return dt * 1 };
    return null;
}

const ApplyNoSpaceMask = function (input = new HTMLInputElement()) {
    input.value = input.value
        .replace(/[ ]+/g, '');
};

const applyAlphaMask = function (input = new HTMLInputElement()) {
    input.value = input.value
        .replace(/[!@#$%¨&*()_+\d\-=¹²³£¢¬§´[`{\/?°ª~\]^}º\\,.;|<>:₢«»"'¶¿®þ]/g, '')
        .replace(/[ ]+/g, ' ');
};

const applyAlphaNumericMask = function (input = new HTMLInputElement()) {
    input.value = input.value
        .replace(/[!@#$%¨&*()_+\-=¹²³£¢¬§´[`{\/?°ª~\]^}º\\,.;|<>:₢«»"'¶¿®þ]/g, '')
        .replace(/[ ]+/g, ' ');
};

const applyPhoneMask = function (input = new HTMLInputElement()) {
    var value = input.value;
    value = value.replace(/\D/g, "");
    value = value.replace(/^(\d{4})(\d{1,4})$/g, "$1-$2");
    value = value.replace(/^(\d{5})(\d{1,4})$/g, "$1-$2");
    value = value.replace(/^(\d{2})(\d{4})(\d{1,4})$/g, "($1) $2-$3");
    value = value.replace(/^(\d{2})(\d{5})(\d{1,4})$/g, "($1) $2-$3");
    input.maxLength = 15;
    input.value = value;
};

const applyUpperMask = function (input = new HTMLInputElement()) {
    input.value = input.value.toUpperCase();
};

const applyLowerMask = function (input = new HTMLInputElement()) {
    input.value = input.value.toLowerCase();
};

const applyDateMask = function (input = new HTMLInputElement()) {
    var text = input.value || "";
    text = text.replace(/\D/g, "");
    text = text.replace(/^(\d{2})(\d+)/g, "$1/$2");
    text = text.replace(/^(\d{2}\/\d{2})(\d{1,4})$/g, "$1/$2");
    if (/^[\d]{2}\/[\d]{2}\/[\d]{4}$/g.test(text)) {
        input.maxLength = text.length;
    }
    input.value = text;
};

jQuery.fn.dateMask = function () {
    applyDateMask(this)
};

const applyDateTimeMask = function (input = new HTMLInputElement()) {
    var value = input.value.replace(/\D/g, "");
    value = value.replace(/^(\d{2})(\d+)$/g, "$1/$2");
    value = value.replace(/^(\d{2}\/\d{2})(\d+)$/g, "$1/$2");
    value = value.replace(/^(\d{2}\/\d{2}\/\d{4})(\d+)$/g, "$1 $2");
    value = value.replace(/^(\d{2}\/\d{2}\/\d{4} \d{2})(\d+)$/g, "$1:$2");
    value = value.replace(/^(\d{2}\/\d{2}\/\d{4} \d{2}:\d{2})(\d+)$/g, "$1:$2");
    input.value = value;
    input.maxLength = 19;
};

const applyDateShortMask = function (input = new HTMLInputElement()) {
    var value = input.value.replace(/\D/g, "");
    value = value.replace(/^(\d{2})(\d+)$/g, "$1/$2");
    value = value.replace(/^(\d{2}\/\d{2})(\d+)$/g, "$1/$2");
    value = value.replace(/^(\d{2}\/\d{2}\/\d{4})(\d+)$/g, "$1 $2");
    value = value.replace(/^(\d{2}\/\d{2}\/\d{4} \d{2})(\d+)$/g, "$1:$2");
    input.value = value;
    input.maxLength = 16;
};

const applyTimeMask = function (input = new HTMLInputElement()) {
    var value = input.value.replace(/\D/g, "");
    value = value.replace(/^(\d{2})(\d+)$/g, "$1:$2");
    input.value = value.replace(/^(\d{2}:\d{2})(\d{1,2})$/g, "$1:$2");
    input.maxLength = 8;
};

const applyShortTimeMask = function (input = new HTMLInputElement()) {
    var value = input.value.replace(/\D/g, "");
    input.value = value.replace(/^(\d{2})(\d{1,2})$/g, "$1:$2");
    input.maxLength = 5;
};

const applyCPForCNPJMask = function (input = new HTMLInputElement()) {
    var value = input.value;
    value = value.replace(/\D/g, "");
    if (value.length <= 11) {
        console.log(value.length);
        value = value.replace(/^(\d{3})(\d+)$/g, "$1.$2");
        value = value.replace(/^(\d{3}\.\d{3})(\d+)$/g, "$1.$2");
        value = value.replace(/^(\d{3}\.\d{3}\.\d{3})(\d{1,2})$/g, "$1-$2");
    } else {
        value = value.replace(/^(\d{2})(\d+)$/g, "$1.$2");
        value = value.replace(/^(\d{2}\.\d{3})(\d+)$/g, "$1.$2");
        value = value.replace(/^(\d{2}\.\d{3}\.\d{3})(\d+)$/g, "$1/$2");
        value = value.replace(/^(\d{2}\.\d{3}\.\d{3}\/\d{4})(\d{1,2})$/g, "$1-$2");
    }
    input.value = value;
    input.maxLength = 18;
};

const applyCPFMask = function (input = new HTMLInputElement()) {
    var text = input.value || "";
    text = text.replace(/\D/g, "");
    text = text.replace(/^(\d{3})(\d+)/g, "$1.$2");
    text = text.replace(/^(\d{3}\.\d{3})(\d+)/g, "$1.$2");
    text = text.replace(/^(\d{3}\.\d{3}\.\d{3})(\d{1,2})$/g, "$1-$2");
    if (/^[\d]{3}\.[\d]{3}\.[\d]{3}-[\d]{2}$/g.test(text)) {
        input.maxLength = text.length;
    }
    input.value = text;
};

const applyCEPMask = function (input = new HTMLInputElement()) {
    var text = input.value || "";
    text = text.replace(/\D/g, "");
    text = text.replace(/^(\d{5})(\d{1,3})$/g, "$1-$2");
    if (/^[\d]{5}-[\d]{3}$/g.test(text)) {
        input.maxLength = text.length;
    }
    input.value = text;
};

const applyCNPJMask = function (input = new HTMLInputElement()) {
    var text = input.value || "";
    text = text.replace(/\D/g, "");
    text = text.replace(/^(\d{2})(\d+)/, "$1.$2");
    text = text.replace(/^(\d{2}\.\d{3})(\d+)/g, "$1.$2");
    text = text.replace(/^(\d{2}\.\d{3}\.\d{3})(\d+)/g, "$1/$2");
    text = text.replace(/^(\d{2}\.\d{3}\.\d{3}\/\d{4})(\d{1,2})$/g, "$1-$2");
    if (/^[\d]{2}\.[\d]{3}\.[\d]{3}\/[\d]{4}-[\d]{2}$/g.test(text)) {
        input.maxLength = text.length;
    }
    input.value = text;
};

const applyCreditCardMask = function (input = new HTMLInputElement()) {
    var text = input.value || "";
    text = text.replace(/\D/g, "");
    text = text.replace(/^(\d{4})(\d+)$/g, "$1 $2");
    text = text.replace(/^(\d{4} \d{4})(\d+)$/g, "$1 $2");
    text = text.replace(/^(\d{4} \d{4} \d{4})(\d{1,4})$/g, "$1 $2");
    if (/^[\d]{4} [\d]{4} [\d]{4} [\d]{4}$/g.test(text)) {
        input.maxLength = text.length;
    }
    input.value = text;
};

const applyNumberMask = function (input = new HTMLInputElement()) {
    var text = input.value || "";
    text = text.replace(/\D/g, "");
    input.value = text;
};

const applyMonthYearMask = function (input = new HTMLInputElement()) {
    var text = input.value || "";
    text = text.replace(/\D/g, "");
    text = text.replace(/^(\d{2})(\d{1,4})/g, "$1/$2");
    if (/^[\d]{2}\/[\d]{4}$/g.test(text)) {
        input.maxLength = text.length;
    }
    input.value = text;
};

const checkLuhn = function (cardNumber) {
    var s = 0;
    var doubleDigit = false;
    cardNumber = cardNumber.replace(/[^\d]+/g, "");
    for (var i = cardNumber.length - 1; i >= 0; i--) {
        var digit = +cardNumber[i];
        if (doubleDigit) {
            digit *= 2;
            if (digit > 9)
                digit -= 9;
        }
        s += digit;
        doubleDigit = !doubleDigit;
    }
    return s % 10 == 0;
};

const validateCardBrand = function (cardNumber) {
    cardNumber = cardNumber.replace(/[^0-9]+/g, "");
    var cards = {
        visa: /^4[0-9]{12}(?:[0-9]{3})/,
        mastercard: /^(5[1-5][0-9]{14}|2(22[1-9][0-9]{12}|2[3-9][0-9]{13}|[3-6][0-9]{14}|7[0-1][0-9]{13}|720[0-9]{12}))$/,
        diners: /^3(?:0[0-5]|[68][0-9])[0-9]{11}/,
        amex: /^3[47][0-9]{13}/,
        discover: /^6(?:011|5[0-9]{2})[0-9]{12}/,
        hiper: /^(606282\d{10}(\d{3})?)|(3841\d{15})/,
        elo: /^((((636368)|(438935)|(504175)|(451416)|(636297))\d{0,10})|((5067)|(4576)|(4011))\d{0,12})/,
        jcb: /^(?:2131|1800|35\d{3})\d{11}/,
        aura: /^(5078\d{2})(\d{2})(\d{11})$/,
        maestro: /^(5018|5020|5038|6304|6759|6761|6763)[0-9]{8,15}$/,
        laser: /^(6304|6706|6709|6771)[0-9]{12,15}$/,
        blanche: /^389[0-9]{11}$/,
        switch: /^(4903|4905|4911|4936|6333|6759)[0-9]{12}|(4903|4905|4911|4936|6333|6759)[0-9]{14}|(4903|4905|4911|4936|6333|6759)[0-9]{15}|564182[0-9]{10}|564182[0-9]{12}|564182[0-9]{13}|633110[0-9]{10}|633110[0-9]{12}|633110[0-9]{13}$/,
        korean: /^9[0-9]{15}$/,
        union: /^(62[0-9]{14,17})$/,
        solo: /^(6334|6767)[0-9]{12}|(6334|6767)[0-9]{14}|(6334|6767)[0-9]{15}$/,
        insta: /^63[7-9][0-9]{13}$/,
        bcglobal: /^(6541|6556)[0-9]{12}$/,
        rupay: /^6[0-9]{15}$/
    };

    for (var flag in cards) {
        if (cards[flag].test(cardNumber)) {
            return flag;
        }
    }

    return false;
};

const validateCNPJ = function (CNPJNumber) {
    CNPJNumber = CNPJNumber.replace(/\D/g, "");

    if (CNPJNumber == '')
        return false;

    if (CNPJNumber.length != 14)
        return false;

    // Elimina CNPJs inválidos conhecidos
    if (CNPJNumber == "00000000000000" ||
        CNPJNumber == "11111111111111" ||
        CNPJNumber == "22222222222222" ||
        CNPJNumber == "33333333333333" ||
        CNPJNumber == "44444444444444" ||
        CNPJNumber == "55555555555555" ||
        CNPJNumber == "66666666666666" ||
        CNPJNumber == "77777777777777" ||
        CNPJNumber == "88888888888888" ||
        CNPJNumber == "99999999999999")
        return false;

    // Valida DVs
    tamanho = CNPJNumber.length - 2;
    numeros = CNPJNumber.substring(0, tamanho);
    digitos = CNPJNumber.substring(tamanho);
    soma = 0;
    pos = tamanho - 7;
    for (i = tamanho; i >= 1; i--) {
        soma += numeros.charAt(tamanho - i) * pos--;
        if (pos < 2)
            pos = 9;
    }
    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    if (resultado != digitos.charAt(0))
        return false;

    tamanho = tamanho + 1;
    numeros = CNPJNumber.substring(0, tamanho);
    soma = 0;
    pos = tamanho - 7;
    for (i = tamanho; i >= 1; i--) {
        soma += numeros.charAt(tamanho - i) * pos--;
        if (pos < 2)
            pos = 9;
    }
    resultado = soma % 11 < 2 ? 0 : 11 - soma % 11;
    if (resultado != digitos.charAt(1))
        return false;

    return true;
};

const validatePassword = function (input) {
    // Create an array and push all possible values that you want in password
    var matchedCase = new Array();
    matchedCase.push(/[!@#$%^&*()_\-+=}{\]\[`~<>?/\\|±!.,]/g);
    matchedCase.push(/[A-Z]/g);
    matchedCase.push(/[0-9]/g);
    matchedCase.push(/[a-z]/g);

    // Check the conditions
    var ctr = 0;
    for (var i = 0; i < matchedCase.length; i++) {
        if (matchedCase[i].test(jQuery(input).val())) {
            ctr++;
        }
    }

    jQuery(input).attr("data-pwstrength", ctr);
    return ctr;
};

if (String.prototype.replaceAll == undefined) {
    String.prototype.replaceAll = function (from, to) {
        var array = this.split(from);
        array = array.filter(function (el) {
            return el !== null;
        });
        return array.join(to);
    };
    console.log("replaceAll added to String.prototype");
}


function searchViaCEP(CEPNumber, homeNumber, delay, callbackFunction) {
    CEPNumber = CEPNumber || "";
    homeNumber = homeNumber || "";
    delay = delay || 0;
    callbackFunction = callbackFunction || function (o) { console.log('No callback defined', o); }
    console.log('Searching CEP', CEPNumber, homeNumber, delay);
    let address = jQuery(".autocomplete.address").prop("disabled");
    let complement = jQuery(".autocomplete.complement").prop("disabled");
    let neighborhood = jQuery(".autocomplete.neighborhood").prop("disabled");
    let city = jQuery(".autocomplete.city").prop("disabled");
    let state = jQuery(".autocomplete.state").prop("disabled");
    let fulladdress = jQuery(".autocomplete.fulladdress").prop("disabled");
    let gia = jQuery(".autocomplete.gia").prop("disabled");
    let ddd = jQuery(".autocomplete.ddd").prop("disabled");
    let ibge = jQuery(".autocomplete.ibge").prop("disabled");
    let siafi = jQuery(".autocomplete.siafi").prop("disabled");

    if (
        (CEPNumber.length == 9 && CEPNumber.includes("-")) ||
        (CEPNumber.length == 8 && CEPNumber.includes("-") == false)
    ) {
        jQuery.ajax({
            type: "GET",
            url: "https://viacep.com.br/ws/" + CEPNumber + "/json/",
            async: true,
            crossorigin: true,
            beforeSend: function () {
                console.log("Getting info from ViaCEP...")
                jQuery(".autocomplete.address").prop("disabled", true);
                jQuery(".autocomplete.complement").prop("disabled", true);
                jQuery(".autocomplete.neighborhood").prop("disabled", true);
                jQuery(".autocomplete.city").prop("disabled", true);
                jQuery(".autocomplete.state").prop("disabled", true);
                jQuery(".autocomplete.fulladdress").prop("disabled", true);
                jQuery(".autocomplete.ibge").prop("disabled", true);
                jQuery(".autocomplete.gia").prop("disabled", true);
                jQuery(".autocomplete.ddd").prop("disabled", true);
                jQuery(".autocomplete.siafi").prop("disabled", true);
            },
            success: function (obj) {
                obj["numero"] = homeNumber;
                if (homeNumber != "") {
                    homeNumber = ", " + homeNumber;
                }

                console.log("ViaCEP Response", obj);

                jQuery(".autocomplete.address:input")
                    .setOrReplaceVal(obj.logradouro)
                    .change().focus();
                jQuery(".autocomplete.complement:input")
                    .setOrReplaceVal(obj.complemento)
                    .change().focus();
                jQuery(".autocomplete.neighborhood:input")
                    .setOrReplaceVal(obj.bairro)
                    .change().focus();
                jQuery(".autocomplete.city:input")
                    .setOrReplaceVal(obj.localidade)
                    .change().focus();
                jQuery(".autocomplete.state:input")
                    .setOrReplaceVal(obj.uf)
                    .change().focus();
                jQuery(".autocomplete.ibge:input")
                    .setOrReplaceVal(obj.ibge)
                    .change().focus();
                jQuery(".autocomplete.gia:input")
                    .setOrReplaceVal(obj.gia)
                    .change().focus();
                jQuery(".autocomplete.ddd:input")
                    .setOrReplaceVal(obj.ddd)
                    .change().focus();
                jQuery(".autocomplete.siafi:input")
                    .setOrReplaceVal(obj.siafi)
                    .change().focus();

                jQuery(".autocomplete.fulladdress:input")
                    .setOrReplaceVal(
                        obj.logradouro +
                        homeNumber +
                        " " +
                        obj.complemento +
                        " - " +
                        obj.bairro +
                        " - " +
                        obj.localidade +
                        " - " +
                        obj.uf
                    )
                    .change().focus();

                jQuery(".autocomplete.address")
                    .not(":input")
                    .text(obj.logradouro);
                jQuery(".autocomplete.complement")
                    .not(":input")
                    .text(obj.complemento);
                jQuery(".autocomplete.neighborhood")
                    .not(":input")
                    .text(obj.bairro);
                jQuery(".autocomplete.city")
                    .not(":input")
                    .text(obj.localidade);
                jQuery(".autocomplete.state")
                    .not(":input")
                    .text(obj.uf);
                jQuery(".autocomplete.ibge")
                    .not(":input")
                    .text(obj.ibge);
                jQuery(".autocomplete.gia")
                    .not(":input")
                    .text(obj.gia);
                jQuery(".autocomplete.siafi")
                    .not(":input")
                    .text(obj.siafi);
                jQuery(".autocomplete.ddd")
                    .not(":input")
                    .text(obj.ddd);
                jQuery(".autocomplete.fulladdress")
                    .not(":input")
                    .text(
                        obj.logradouro +
                        homeNumber +
                        " - " +
                        obj.bairro +
                        " - " +
                        obj.localidade +
                        " - " +
                        obj.uf
                    );

                if (obj.logradouro) {
                    setTimeout(function () {
                        jQuery(".autocomplete.num:input, .autocomplete.number:input").focus();
                        jQuery(".autocomplete.homenum:input, .autocomplete.homenumber:input").focus();
                    }, delay);
                } else {
                    console.error('Address not found');
                    let nft = jQuery(this).attr("data-addressnotfoundtext") || jQuery(this).attr("data-notfoundtext") || "";
                    jQuery(".autocomplete.fulladdress")
                        .not(":input").text(nft);
                    jQuery(".autocomplete.fulladdress:input")
                        .val(nft).change();
                    eval(jQuery(this).attr("data-addressnotfound") || jQuery(this).attr("data-notfound") || "void(0)");
                    setTimeout(function () {
                        jQuery(".autocomplete.address:input").focus();
                    }, delay);
                }
                if (obj) callbackFunction(obj);
            },
            error: function (xhr, ajaxOptions, thrownError) {
                //Error event
                console.log("VIACEP error", xhr, ajaxOptions, thrownError);
                setTimeout(function () {
                    jQuery(".autocomplete.address:input").focus();
                }, delay);
            },
            complete: function () {
                jQuery(".autocomplete.address").prop("disabled", address);
                jQuery(".autocomplete.complement").prop("disabled", complement);
                jQuery(".autocomplete.neighborhood").prop("disabled", neighborhood);
                jQuery(".autocomplete.city").prop("disabled", city);
                jQuery(".autocomplete.state").prop("disabled", state);
                jQuery(".autocomplete.fulladdress").prop("disabled", fulladdress);
                jQuery(".autocomplete.gia").prop("disabled", gia);
                jQuery(".autocomplete.ddd").prop("disabled", ddd);
                jQuery(".autocomplete.siafi").prop("disabled", siafi);
                jQuery(".autocomplete.ibge").prop("disabled", ibge);
            }
        });
    } else {
        console.log("Awaiting a valid CEP", CEPNumber);
    }
}


jQuery.fn.setOrReplaceVal = function (value) {

    let valor = jQuery.trim(jQuery(this).val() || "");
    if (valor == "") {
        jQuery(this).val(value)
    } else if (jQuery(this).is(".noreplace") == false) {
        jQuery(this).val(value)
    }
    return jQuery(this);

}




jQuery.fn.isValid = function () {
    let results = [];

    if (jQuery(this).length > 1 || jQuery(this).prop("tagName") == "FORM") {
        eval(jQuery(this).attr("data-beforevalidatecallback") || "void(0)");
        var elements = [];
        var config = Array.prototype.slice.call(arguments)[0];
        jQuery(this)
            .find(":input" + (config || ""))
            .each(function () {
                results.push(jQuery(this).isValid());
                elements.push(jQuery(this));
            });

        for (var mm = 0; mm < results.length; mm++) {
            if (results[mm] === false) {
                eval(jQuery(this).attr("data-invalidcallback") || "void(0)");
                eval(jQuery(this).attr("data-aftervalidatecallback") || "void(0)");
                return false;
            }
        }
        eval(jQuery(this).attr("data-validcallback") || "void(0)");
        eval(jQuery(this).attr("data-aftervalidatecallback") || "void(0)");
        return true;
    } else {
        this.removeClass("error");
        this.removeClass("success");
        this.closest(".form-group").removeClass("has-error");
        if (this.get(0).setCustomValidity) {
            this.get(0).setCustomValidity("");
        }
        var valids = [];
        var allargs = Array.prototype.slice.call(arguments);
        for (var vv = 0; vv < allargs.length; vv++) {
            allargs[vv].split(" ").forEach(function (el) {
                valids.push(el);
            });
        }
        var value = this.val();
        var type = this.attr("type");
        if (arguments.length < 1) {
            var classes = (this.attr("class") || "").split(" ");
            for (var vc = 0; vc < classes.length; vc++) {
                valids.push("" + classes[vc]);
            }
        }
        for (var i = 0; i < valids.length; i++) {
            if (this.prop("disabled") == false) {
                switch (valids[i].toLowerCase()) {
                    case "nospace":
                        if (jQuery.trim(value) === "") {
                            results.push(true);
                            break;
                        }
                        results.push(value.indexOf(" ") < 0);
                        break;
                    case "number":
                    case "num":
                        if (jQuery.trim(value) === "") {
                            results.push(true);
                            break;
                        }
                        results.push(!isNaN(value.replaceAll(",", ".")));
                        break;
                    case "upper":
                        if (jQuery.trim(value) === "") {
                            results.push(true);
                            break;
                        }
                        results.push(!/[a-z]/.test(value));
                        break;
                    case "lower":
                        if (jQuery.trim(value) === "") {
                            results.push(true);
                            break;
                        }
                        results.push(!/[A-Z]/.test(value));

                        break;
                    case "alphanumeric":
                    case "alphanum":
                        if (jQuery.trim(value) === "") {
                            results.push(true);
                            break;
                        }
                        results.push(/^[A-Za-z0-9 ]+$/.test(value));
                        break;
                    case "alpha":
                        if (jQuery.trim(value) === "") {
                            results.push(true);
                            break;
                        }
                        results.push(/^[A-Za-z ]+$/.test(value));
                        break;
                    case "tel":
                    case "cel":
                    case "telephone":
                    case "mobilephone":
                        if (jQuery.trim(value) === "") {
                            results.push(true);
                            break;
                        }
                        value = value
                            .replaceAll("(", "")
                            .replaceAll(")", "")
                            .replaceAll(" ", "")
                            .replaceAll("-", "");
                        results.push(!isNaN(value) && value.length >= 8);
                        break;
                    case "mail":
                    case "email":
                    case "e-mail":
                        if (jQuery.trim(value) === "") {
                            results.push(true);
                            break;
                        }
                        var re = /^[\w-]+(\.[\w-]+)*@[\w]+(\.[a-z]{2,6})*(\.[a-z]{2,6})$/gi;
                        results.push(re.test(value));
                        break;
                    case "required":
                    case "req":
                    case "obg":
                        if (type == "checkbox" || type == "radio") {
                            results.push(jQuery(this).is(":checked"));
                        } else {
                            results.push(!(!value || jQuery.trim(value) === ""));
                        }
                        break;
                    case "url":
                    case "link":
                        if (jQuery.trim(value) === "") {
                            results.push(true);
                            break;
                        }
                        var re = /^(http[s]?:\/\/){0,1}(www\.){0,1}[a-zA-Z0-9\.\-]+\.[a-zA-Z]{2,5}[\.]{0,1}/;
                        results.push(re.test(value));
                        break;
                    case "data":
                    case "date":
                        if (jQuery.trim(value) === "") {
                            results.push(true);
                            break;
                        }
                        results.push(value.split("/").length == 3)
                        results.push(validDate(value));
                        break;
                    case "datetime":
                    case "dateshorttime":
                    case "datetimeshort":
                        if (jQuery.trim(value) === "") {
                            results.push(true);
                            break;
                        }
                        var comp = value.split(" ");
                        if (comp.length == 2) {
                            results.push(validDate(comp[0]) && validateTime(comp[1]));
                            break;
                        }
                        results.push(false);
                        break;
                    case "time":
                    case "shorttime":
                    case "timeshort":
                        if (jQuery.trim(value) === "") {
                            results.push(true);
                            break;
                        }
                        results.push(validateTime(value));
                        break;
                    case "month":
                        if (jQuery.trim(value) === "") {
                            results.push(true);
                            break;
                        }
                        results.push(jQuery(this).isValid("num"));
                        var num = parseInt(value);
                        results.push(num > 0 && num <= 12);
                        break;
                    case "monthyear":
                        if (jQuery.trim(value) === "") {
                            results.push(true);
                            break;
                        }
                        var mesano = value.split("/");
                        if (mesano.length == 2) {
                            results.push(mesano[0] > 0 && mesano[0] <= 12);
                            results.push(!isNaN(mesano[1]));
                            break;
                        }
                        if (mesano.length == 3) {
                            results.push(mesano[1] > 0 && mesano[1] <= 12);
                            results.push(!isNaN(mesano[2]));
                            break;
                        }
                        results.push(false);
                        break;
                    case "cep":
                        if (jQuery.trim(value) === "") {
                            results.push(true);
                            break;
                        }
                        results.push(value.replace("-", "").length == 8);
                        break;
                    case "cpfcnpj":
                    case "cnpjcpf":
                        if (jQuery.trim(value) === "") {
                            results.push(true);
                            break;
                        }
                        results.push(jQuery(this).isValid("cpf") || jQuery(this).isValid("cnpj"));
                        break;
                    case "cnpj":
                        if (jQuery.trim(value) === "") {
                            results.push(true);
                            break;
                        }
                        results.push(validateCNPJ(value));
                        break;
                    case "cpf":
                        if (jQuery.trim(value) === "") {
                            results.push(true);
                            break;
                        }
                        value = value.replace(/\D/g, "");

                        // Elimina CPFS invalidos conhecidos
                        if (
                            value == "00000000000" ||
                            value == "11111111111" ||
                            value == "22222222222" ||
                            value == "33333333333" ||
                            value == "44444444444" ||
                            value == "55555555555" ||
                            value == "66666666666" ||
                            value == "77777777777" ||
                            value == "88888888888" ||
                            value == "99999999999" ||
                            value.length !== 11
                        ) {
                            results.push(false);
                            break;
                        }

                        var Soma = 0;
                        var Resto = 0;

                        for (x = 1; x <= 9; x++)
                            Soma = Soma + parseInt(value.substring(x - 1, x)) * (11 - x);
                        Resto = (Soma * 10) % 11;

                        if (Resto == 10 || Resto == 11) Resto = 0;
                        if (Resto != parseInt(value.substring(9, 10))) {
                            results.push(false);
                            break;
                        }

                        Soma = 0;
                        for (x = 1; x <= 10; x++)
                            Soma = Soma + parseInt(value.substring(x - 1, x)) * (12 - x);
                        Resto = (Soma * 10) % 11;

                        if (Resto == 10 || Resto == 11) Resto = 0;
                        if (Resto != parseInt(value.substring(10, 11))) {
                            results.push(false);
                            break;
                        }
                        results.push(true);
                        break;
                    case "debitcard":
                    case "creditcard":
                        if (jQuery.trim(value) === "") {
                            results.push(true);
                            break;
                        }
                        var vlu = checkLuhn(value);

                        if (vlu) {
                            var flagcard = validateCardBrand(value);
                            jQuery(this).attr("data-flagcard", flagcard.toString());
                            if (
                                jQuery(this).is(
                                    ".visa, .mastercard, .diners, .amex, .discover, .hiper, .elo, .jcb, .aura, .maestro, .laser, .blanche, .switch, .korean, .union, .solo, .insta, .bcglobal, .rupay"
                                )
                            ) {
                                if (flagcard) {
                                    results.push(jQuery(this).hasClass(flagcard.toString()));
                                } else {
                                    results.push(false);
                                }
                            } else {
                                results.push(flagcard !== false);
                            }
                        } else {
                            jQuery(this).attr("data-flagcard", false);
                            results.push(false);
                        }
                        break;
                    case "password":
                        if (jQuery.trim(value) === "") {
                            results.push(true);
                            break;
                        }

                        var strenght = valids[i + 1] || "3";

                        switch (strenght) {
                            case "strong":
                                strenght = 4;
                                break;
                            case "medium":
                                strenght = 3;
                                break;
                            default:
                                if (isNaN(strenght)) {
                                    strenght = 3;
                                }
                                break;
                        }

                        results.push(validatePassword(this) >= strenght);
                        break;

                    case "after":
                    case "before":

                        if (jQuery.trim(value) === "") {
                            results.push(true);
                            break;
                        }
                        if (typeof valids[i + 1] === "undefined") {
                            results.push(false);
                            break;
                        }

                        var num = valids[i + 1] || "0";
                        if ((num.indexOf("today") || num.indexOf("/")) && validDate(value)) {
                            value = parseDate(value);
                            if (num == "today") {
                                num = Date.now();
                            } else {
                                num = parseDate(num);
                            }
                        }
                        debugger;
                        if (valids[i] == "after") {
                            results.push(parseFloat(value) >= parseFloat(num));
                        } else {
                            results.push(parseFloat(value) <= parseFloat(num));
                        }
                        break;
                    case "eq":
                    case "equal":
                        if (jQuery.trim(value) === "") {
                            results.push(true);
                            break;
                        }
                        if (typeof valids[i + 1] === "undefined") {
                            results.push(false);
                            break;
                        }
                        var selector =
                            jQuery(this).attr("data-eq") ||
                            jQuery(this).data("data-equal") ||
                            valids[i + 1] ||
                            null;
                        var valor1 = jQuery(this).val();
                        var valor2 = jQuery(selector).val() || jQuery(selector).text();
                        results.push(valor1 == valor2);
                        break;
                    case "notchars":
                    case "notchar":
                    case "containsnotchar":
                    case "containsnotchars":
                    case "notcontainschars":
                    case "notcontainschar":
                    case "eqv":
                    case "equalvalue":
                    case "equal-value":
                    case "eq-v":
                    case "contains":
                    case "cnts":
                    case "containsstring":
                    case "containsanychar":
                    case "containschar":
                    case "containsanychars":
                    case "containsallchar":
                    case "containsallchars":

                        if (jQuery.trim(value) === "") {
                            results.push(true);
                            break;
                        }
                        var valor2 =
                            jQuery(this).attr("data-cnts") ||
                            jQuery(this).attr("data-contains") ||
                            jQuery(this).attr("data-string") ||
                            jQuery(this).attr("data-value") ||
                            valids[i + 1] ||
                            "";

                        if (valor2 === "") {
                            results.push(false);
                            break;
                        }

                        var valor1 = jQuery(this).val();
                        if (valor2.toLowerCase() == "_space") {
                            valor2 = " ";
                        }

                        switch (valids[i].toLowerCase()) {
                            case "containsanychar":
                            case "containsanychars":
                                results.push(validateAnyChar(valor1, valor2));
                                break;
                            case "containschar":
                            case "containsallchar":
                            case "containsallchars":
                                results.push(validateAllChar(valor1, valor2));
                                break;
                            case "eqv":
                            case "equalvalue":
                            case "equal-value":
                            case "eq-v":
                                results.push(valor1 == valor2);
                                break;
                            case "notchars":
                            case "notchar":
                            case "containsnotchar":
                            case "containsnotchars":
                            case "notcontainschars":
                            case "notcontainschar":
                                results.push(validateNotChar(valor1, valor2));
                                break;
                            default:
                                results.push(valor1.includes(valor2));
                                break;
                        }
                        break;

                    case "len":
                        if (jQuery.trim(value) === "") {
                            results.push(true);
                            break;
                        }
                        if (typeof valids[i + 1] === "undefined") {
                            results.push(false);
                            break;
                        }
                        results.push(value.length == parseInt(valids[i + 1]));
                        break;
                    case "minlen":
                        if (jQuery.trim(value) === "") {
                            results.push(true);
                            break;
                        }
                        if (typeof valids[i + 1] === "undefined") {
                            results.push(false);
                            break;
                        }
                        results.push(value.length >= parseInt(valids[i + 1]));
                        break;
                    case "maxlen":
                        if (jQuery.trim(value) === "") {
                            results.push(true);
                            break;
                        }
                        if (typeof valids[i + 1] === "undefined") {
                            results.push(false);
                            break;
                        }
                        results.push(value.length <= parseInt(valids[i + 1]));
                        break;
                    case "to":
                        if (jQuery.trim(value) === "") {
                            results.push(true);
                            break;
                        }
                        if (typeof valids[i + 1] === "undefined") {
                            results.push(false);
                            break;
                        }
                        if (typeof valids[i - 1] === "undefined") {
                            results.push(false);
                            break;
                        }
                        var v1 = jQuery(this).isValid("after " + valids[i - 1]);
                        var v2 = jQuery(this).isValid("before " + valids[i + 1]);
                        results.push(v1 && v2);
                        break;
                    case "minage":
                        if (jQuery.trim(value) === "") {
                            results.push(true);
                            break;
                        }
                        var idade = getAge(value);
                        results.push(idade >= parseInt(valids[i + 1]));
                        break;
                    case "maxage":
                        if (jQuery.trim(value) === "") {
                            results.push(true);
                            break;
                        }
                        var idade = getAge(value);
                        results.push(idade <= parseInt(valids[i + 1]));
                        break;
                    case "age":
                        if (jQuery.trim(value) === "") {
                            results.push(true);
                            break;
                        }
                        var idade = getAge(value);
                        results.push(idade == parseInt(valids[i + 1]));
                        break;
                    default:
                        results.push(true);
                        break;
                }
            } else {
                results.push(true);
            }
        }

        for (var i = 0; i < results.length; i++) {
            if (results[i] === false) {
                jQuery(this).addClass("error");
                jQuery(this)
                    .closest(".form-group")
                    .addClass("has-error");
                jQuery(this)
                    .get(0)
                    .setCustomValidity(jQuery(this).attr("data-invalidmessage") || "");
                eval(jQuery(this).attr("data-invalidcallback") || "void(0)");
                eval(jQuery(this).attr("data-aftervalidatecallback") || "void(0)");
                return false;
            }
        }

        if (jQuery.trim(value) !== "") {
            jQuery(this).addClass("success");
        }
        jQuery(this).removeClass("error");
        eval(jQuery(this).attr("data-validcallback") || "void(0)");
        eval(jQuery(this).attr("data-aftervalidatecallback") || "void(0)");
        return true;
    }
};

jQuery(document).ready(function () {
    jQuery('form.validate, form[data-validate="true"], form[data-validation="true"], .forcevalidate').startValidation().startMasks()
});

jQuery.fn.startMasks = function () {

    jQuery(this).find(".mask.phone, .mask.tel, .mask.cel").phoneMask();
    jQuery(this).find(".mask.upper").upperMask();
    jQuery(this).find(".mask.lower, .mask.email, .mask.mail").lowerMask();
    jQuery(this).find(".mask.cpf").cpfMask();
    jQuery(this).find(".mask.cep").cepMask();
    jQuery(this).find(".mask.cnpj").cnpjMask();
    jQuery(this).find(".mask.cpfcnpj, .mask.cnpjcpf").cpfCnpjMask();
    jQuery(this).find(".mask.creditcard, .mask.debitcard").creditCardMask();
    jQuery(this).find(".mask.date, .mask.data").dateMask();
    jQuery(this).find(".mask.monthyear").monthYearMask();
    jQuery(this).find(".mask.num, .mask.number, .mask.month").numberMask();
    jQuery(this).find(".mask.len").lenMask();
    jQuery(this).find(".autocomplete.cep").cepAutoComplete();
    jQuery(this).find(".mask.time").timeMask();
    jQuery(this).find(".mask.dateshorttime, .mask.datetimeshort").shortTimeMask();
    jQuery(this).find(".mask.datetime").dateTimeMask();
    jQuery(this).find(".mask.alpha").alphaMask();
    jQuery(this).find(".mask.alphanum, .mask.alphanumeric").alphaNumericMask();
    jQuery(this).find(".mask.nospace").noSpaceMask();
    jQuery(this).find(".mask.maxlen").maxLenMask();

}

jQuery.fn.startValidation = function () {
    jQuery(this).not(".notonblur").validateOnBlur();
    jQuery(this).not(".notonchange").validateOnChange();
    jQuery(this).find(".onkeyup").validateOnType();
    return jQuery(this).on("submit", function () {
        return jQuery(this).isValid();
    });

}

jQuery.fn.validateOnType = function (time) {
    time = time || 900;
    let x = jQuery(this)
        .on("keyup", function () {
            var p = jQuery(this);
            p.removeClass("error");
            p.closest(".form-group").removeClass("has-error");
            clearTimeout(onTypeTimeout);
            onTypeTimeout = setTimeout(function () {
                p.isValid();
            }, time);
        });
    console.log("InnerFormValidation:", "Validation on Type started", x, "delay", time);
    return x;
}

jQuery.fn.validateOnBlur = function () {
    let x = jQuery(this)
        .on("blur", function () {
            jQuery(this).isValid();
        });
    console.log("InnerFormValidation:", "Validation on Blur started", x);
    return x;
}

jQuery.fn.validateOnChange = function () {
    let x = jQuery(this)
        .on("change", function () {
            jQuery(this).isValid();
        });
    console.log("InnerFormValidation:", "Validation on Change started", x);
    return x;
}

jQuery.fn.phoneMask = function () {
    let x = jQuery(this).on("input", function () {
        applyPhoneMask(this);
    });
    console.log("InnerFormValidation:", "PhoneMask started", x);
    return x;
}


jQuery.fn.upperMask = function () {
    let x = jQuery(this).on("input", function () {
        applyUpperMask(this);
    });
    console.log("InnerFormValidation:", "UpperMask started", x);
    return x;
}


jQuery.fn.lowerMask = function () {
    let x = jQuery(this).on("input", function () {
        applyLowerMask(this);
    });
    console.log("InnerFormValidation:", "LowerMask started", x);
    return x;
}

jQuery.fn.cpfMask = function () {
    let x = jQuery(this).on("input", function () {
        applyCPFMask(this);
    });
    console.log("InnerFormValidation:", "CpfMask started", x);
    return x;
}


jQuery.fn.cepMask = function () {
    let x = jQuery(this).on("input", function () {
        applyCEPMask(this);
    });
    console.log("InnerFormValidation:", "CepMask started", x);
    return x;
}


jQuery.fn.cnpjMask = function () {
    let x = jQuery(this).on("input", function () {
        applyCNPJMask(this);
    });
    console.log("InnerFormValidation:", "CnpjMask started", x);
    return x;
}

jQuery.fn.cpfCnpjMask = function () {
    let x = jQuery(this).on('input', function () {
        applyCPForCNPJMask(this);
    });
    console.log("InnerFormValidation:", "CpfCnpjMask started", x);
    return x;
}


jQuery.fn.creditCardMask = function () {
    let x = jQuery(this).on("input", function () {
        applyCreditCardMask(this);
    });
    console.log("InnerFormValidation:", "CreditCardMask started", x);
    return x;
}

jQuery.fn.dateMask = function () {
    let x = jQuery(this).on("input", function () {
        applyDateMask(this);
    });
    console.log("InnerFormValidation:", "DateMask started", x);
    return x;
}

jQuery.fn.monthYearMask = function () {
    let x = jQuery(this).on("input", function () {
        applyMonthYearMask(this);
    });
    console.log("InnerFormValidation:", "MonthYearMask started", x);
    return x;
}

jQuery.fn.numberMask = function () {
    let x = jQuery(this).on("input", function () {
        applyNumberMask(this);
    });
    console.log("InnerFormValidation:", "NumberMask started", x);
    return x;
}

jQuery.fn.lenMask = function (tam) {
    let x = jQuery(this).on("input", function () {
        var array = jQuery(this)
            .attr("class")
            .split(" ")
            .filter(function (el) {
                return el != null && el != "";
            });
        tam = tam || parseInt(array[array.indexOf("len") + 1]);
        if (!isNaN(tam)) {
            jQuery(this).attr("maxlength", tam);
            jQuery(this).val(
                jQuery(this)
                    .val()
                    .substring(0, tam)
            );
        }
    });
    console.log("InnerFormValidation:", "LenMax started", x);
    return x;
}

jQuery.fn.cepAutoComplete = function () {
    let x = jQuery(this).on("input", function () {
        searchViaCEP(
            jQuery(this).val(),
            jQuery(".autocomplete.homenum").val() || jQuery(".autocomplete.homenumber").val() || jQuery(".autocomplete.number").val() || jQuery(".autocomplete.num").val(),
            jQuery(this).data('timeout') || 0
        );
    });
    console.log("InnerFormValidation:", "Autocomplete for CEP started", x);
}

jQuery.fn.timeMask = function () {
    let x = jQuery(this).on("input", function () {
        applyTimeMask(this);
    });
    console.log("InnerFormValidation:", "TimeMask started", x);
    return x;
}

jQuery.fn.shortTimeMask = function () {
    let x = jQuery(this).on("input", function () {
        applyShortTimeMask(this);
    });
    console.log("InnerFormValidation:", "ShortTimeMask started", x);
    return x;

}

jQuery.fn.dateShortTimeMask = function () {
    let x = jQuery(this).on("input", function () {
        applyDateShortMask(this);
    });
    console.log("InnerFormValidation:", "DateShortTimeMask started", x);
    return x;
}

jQuery.fn.dateTimeMask = function () {
    let x = jQuery(this).on("input", function () {
        applyDateTimeMask(this);
    });
    console.log("InnerFormValidation:", "DateTimeMask started", x);
    return x;
}

jQuery.fn.alphaMask = function () {
    let x = jQuery(this).on("input", function () {
        applyAlphaMask(this);
    });
    console.log("InnerFormValidation:", "AlphaMask started", x);
    return x;
}

jQuery.fn.alphaNumericMask = function () {
    let x = jQuery(this).on("input", function () {
        applyAlphaNumericMask(this);
    });
    console.log("InnerFormValidation:", "AlphaNumericMask started", x);
    return x;
}


jQuery.fn.noSpaceMask = function () {
    let x = jQuery(this).on("input", function () {
        ApplyNoSpaceMask(this);
    });
    console.log("InnerFormValidation:", "NoSpaceMask started", x);
    return x;
}

jQuery.fn.maxLenMask = function () {
    let x = jQuery(this).on("input", function () {
        var array = jQuery(this)
            .attr("class")
            .split(" ")
            .filter(function (el) {
                return el != null && el != "";
            });
        var tam = parseInt(array[array.indexOf("len") + 1]);
        if (!isNaN(tam)) {
            jQuery(this).attr("maxlength", tam);
            jQuery(this).val(
                jQuery(this)
                    .val()
                    .substring(0, tam + 1)
            );
        }
    });
    console.log("InnerFormValidation:", "MaxLenMask started", x);
    return x;
}

console.log('InnerFormValidation loaded');