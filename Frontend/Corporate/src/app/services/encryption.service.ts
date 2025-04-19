import { Injectable } from '@angular/core';
import * as CryptoJS from 'crypto-js';
// import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EncryptionService {

// fek = environment.fek;
// bek = environment.bek;
// b = environment.b;

key: string = 'ILoveJesusPlenty'

constructor() { }

e(value: string): string {
  return CryptoJS.AES.encrypt(value, this.key).toString();
};

d(textToDecrypt: string) {
  return CryptoJS.AES.decrypt(textToDecrypt, this.key).toString(CryptoJS.enc.Utf8);
};

eD(data: string): string {
  const key = CryptoJS.enc.Utf8.parse(this.key);

  const encrypted = CryptoJS.AES.encrypt(data, key, {
    mode: CryptoJS.mode.ECB,
    padding: CryptoJS.pad.Pkcs7
  });
  return encrypted.toString();
};

dD(data: string) {
  const ciphertext = data;
  const key = CryptoJS.enc.Utf8.parse(this.key);

  const decryptedBytes = CryptoJS.AES.decrypt(ciphertext, key, {
    mode: CryptoJS.mode.ECB,
    padding: CryptoJS.pad.Pkcs7
  });
  return CryptoJS.enc.Utf8.stringify(decryptedBytes);
};



}