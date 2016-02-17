'use strict';

import {
  NativeModules
} from 'react-native';

let NativeStorage = NativeModules.Storage;

let Storage = {

  saveValue: function(key, value) {
    return new Promise((resolve, reject) => {
      NativeStorage.saveValue(key, value, function(err) {
        if (err) {
          reject(err);
        } else {
          resolve();
        }
      });
    });
  },

  getValue: function(key) {
    return new Promise((resolve, reject) => {
      NativeStorage.getValue(key, function(err, value) {
        if((!err && arguments.length === 1) || value === '') {
          err = 'value is empty or not found';
        }
        if (err) {
          reject(err);
        } else {
          resolve({value});
        }
      });
    });
  },

  resetValue: function(key) {
    return new Promise((resolve, reject) => {
      NativeStorage.resetValue(key, function(err) {
        if (err) {
          reject(err);
        } else {
          resolve();
        }
      });
    });
  },

};

export default Storage;