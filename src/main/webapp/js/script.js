var hangman; //global state

$(document).ready(function() {
    initialiseHangman();

    drawEmptyBoard();
    drawKeyboard();

    //listen for clicks on the on-screen keyboard
    $('.letter').click(function() {
        var letter = $(this).html();

        selectLetter(letter);
    });

    //listen for presses on the real keyboard
    $('html').keypress(function(e) {
        var key = String.fromCharCode(e.which);
        var match = /[A-Z]/i.exec(key);

        if (match) {
            var letter = match[0].toUpperCase();

            selectLetter(letter);
        }
    });
});

function initialiseHangman() {
    $.ajax({
        url: '/start/',
        async: false,
        dataType: 'json',
        success: function(data) {
            hangman = {
                phrase: data.phrase.toUpperCase(),
                remainingMoves: data.remaining_moves,
                selectedLetters: []
            };
        }
    });
}

function drawEmptyBoard() {
    var dashes = '';

    for (var i = 0; i < hangman.phrase.length; i++) {
        if (/[A-Z]/i.exec(hangman.phrase[i])) {
            dashes += '_';
        }
        else {
            dashes += hangman.phrase[i];
        }
    }

    $('#phrase').html(dashes);
}

function drawKeyboard() {
    var letters = alphabet();

    letters.forEach(function(e) {
        $('#letters').append('<div class="letter">' + e + '</div>');
    });
}

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

function selectLetter(letter) {
    if (hangman.selectedLetters.indexOf(letter) >= 0) {
        return;
    }

    if (hangman.remainingMoves > 0) {
        $.ajax({
            url: '/move/',
            type: 'POST',
            dataType: 'json',
            data: {
                'letter': letter
            },
            success: function(data) {
                hangman.remainingMoves = data.remaining_moves;
                hangman.selectedLetters.push(letter);

                updateKeyboard(letter);
                updateBoard(letter);
                updateAttemptsRemaining();

                if (data.win) {
                    showWin();
                }

                if (data.remaining_moves == 0) {
                    showGameOver();
                }
            }
        });
    }
}

function updateKeyboard(letter) {
    var letterElement = findLetterElement(letter);

    $(letterElement).css('color', '#F1F1F1');
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

function updateBoard(letter) {
    var currentPhraseDisplayed = $('#phrase').html();
    var newPhrase = '';
    for (var i = 0; i < hangman.phrase.length; i++) {
        if (hangman.phrase[i] == letter) {
            newPhrase += letter;
        }
        else {
            newPhrase += currentPhraseDisplayed[i];
        }
    }

    $('#phrase').html(newPhrase);
}

function updateAttemptsRemaining() {
    $('#attempts-remaining').html(hangman.remainingMoves);
}

function showWin() {
    $('#instruction').hide();
    $('#win-message').show();
}

function showGameOver() {
    $('#instruction').hide();
    $('#lose-message').show();
    $('#phrase').css('color', '#FF0000');
}
