import NativeModules from 'react-native';

var NativeStorage = NativeModules.Storage;

var Storage = {

  setGenericPassword: function(name, password, callback) {
    return new Promise((resolve, reject) => {
      NativeStorage.setGenericPassword(name, password, function(err) {
        callback && callback((err && convertError(err)) || null);
        if (err) {
          reject(convertError(err));
        } else {
          resolve();
        }
      });
    });
  },

  getGenericPassword: function(
    service?: string,
    callback?: ?(error: ?Error, result: ?string) => void
  ): Promise {
    return new Promise((resolve, reject) => {
      NativeStorage.getGenericPassword(service, function(err, username, password) {
        err = convertError(err);
        if(!err && arguments.length === 1) {
          err = new Error('No keychain entry found' + (service ? ' for service "' + service + '"' : ''));
        }
        callback && callback((err && convertError(err)) || null, username, password);
        if (err) {
          reject(convertError(err));
        } else {
          resolve({ username, password });
        }
      });
    });
  },

  resetGenericPassword: function(
    service?: string,
    callback?: ?(error: ?Error) => void
  ): Promise {
    return new Promise((resolve, reject) => {
      NativeStorage.resetGenericPassword(service, function(err) {
        callback && callback((err && convertError(err)) || null);
        if (err) {
          reject(convertError(err));
        } else {
          resolve();
        }
      });
    });
  },

};

function convertError(err) {
  if (!err) {
    return null;
  }
  let out = new Error(err.message);
  out.key = err.key;
  return out;
}

export default Storage;