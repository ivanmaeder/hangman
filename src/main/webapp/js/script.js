var phrase; //TODO put all this in an object or something

var TOTAL_POSSIBLE_INCORRECT_MOVES = 6; //TODO load all this from back-end, including partially complete games
var incorrectMoves = 0;
var selectedLetters = [];

$(document).ready(function() {
    phrase = fetchNewPhrase();
    drawPhrase(phrase);
    drawLetters();

    $('.letter').click(function() {
        var letter = $(this).html();

        selectLetter(letter);
    });

    $('html').keypress(function(e) {
        var key = String.fromCharCode(e.which);
        var match = /[A-Z]/i.exec(key);

        if (match) {
            var letter = match[0].toUpperCase();

            selectLetter(letter);
        }
    });
});

function alphabet() {
    var alphabet = [];

    var A = "A".charCodeAt(0);
    var Z = "Z".charCodeAt(0);

    //for A..Z
    for (var i = A; i <= Z; i++) {
        var letter = String.fromCharCode(i);

        alphabet.push(letter);
    }

    return alphabet;
}

function drawLetters() {
    var letters = alphabet();

    letters.forEach(function(e) {
        $('#letters').append('<div class="letter">' + e + '</div>');
    });
}

function fetchNewPhrase() {
    return 'Pulp Fiction'.toUpperCase();
}

function drawPhrase(phrase) {
    var dashes = '';

    for (var i = 0; i < phrase.length; i++) {
        if (/[A-Z]/i.exec(phrase[i])) {
            dashes += '_';
        }
        else {
            dashes += phrase[i];
        }
    }

    $('#phrase').html(dashes);
}

function findLetterElement(letter) {
    var letterElement;

    $('#letters').find('div').each(function(i, e) {
        if (letter == $(e).html()) {
            letterElement = e;
            return; //break for jQuery each()
        }
    });

    return letterElement;
}

function selectLetter(letter) {
    if (selectedLetters.indexOf(letter) >= 0) {
        return;
    }

    if (incorrectMoves < TOTAL_POSSIBLE_INCORRECT_MOVES) {
        var letterElement = findLetterElement(letter);

        $(letterElement).css('color', '#F1F1F1');
        /*
        Red color scheme for incorrect letters:
            #FA9898
            #FF9C9C
        */
        //send to server
        //animate in
        //display as a letter chosen
        selectedLetters.push(letter);

        var currentPhraseDisplayed = $('#phrase').html();
        var newPhrase = '';
        var correctMove = false;
        for (var i = 0; i < phrase.length; i++) {
            if (phrase[i] == letter) {
                newPhrase += letter;
                correctMove = true;
            }
            else {
                newPhrase += currentPhraseDisplayed[i];
            }
        }
        $('#phrase').html(newPhrase);

        if (!correctMove) {
            incorrectMoves++;

            updateAttemptsRemaining();
        }
    }
}

function updateAttemptsRemaining() {
    $('#attempts-remaining').html(TOTAL_POSSIBLE_INCORRECT_MOVES - incorrectMoves);
}
