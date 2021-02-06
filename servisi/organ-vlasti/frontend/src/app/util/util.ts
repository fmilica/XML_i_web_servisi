export function onlyContainsLetters(myString: string): boolean {
    return /^[a-zšđčćžA-ZŠĐČĆŽ]+$/.test(myString);
}

export function onlyContainsLettersAndSpaces(myString: string): boolean {
    return /^[a-zšđčćžA-ZŠĐČĆŽ ']+$/.test(myString);
}

export function smoothScroll(): void {
    const scrollToTop = window.setInterval(() => {
        const pos = window.pageYOffset;
        if (pos > 0) {
            window.scrollTo(0, pos - 20); // how far to scroll on each step
        } else {
            window.clearInterval(scrollToTop);
        }
    }, 16);
}

export function arrayBufferToBase64( buffer ) {
    var binary = '';
    var bytes = new Uint8Array( buffer );
    var len = bytes.byteLength;
    for (var i = 0; i < len; i++) {
        binary += String.fromCharCode( bytes[ i ] );
    }
    return window.btoa( binary );
  }