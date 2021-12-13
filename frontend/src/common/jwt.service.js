const USER_KEY = "user";

export const getUser = () => {
  return JSON.parse(window.localStorage.getItem(USER_KEY));
};

export const saveUser = user => {
  window.localStorage.setItem(USER_KEY,JSON.stringify(user));
};

export const destroyUser = () => {
  window.localStorage.removeItem(USER_KEY);
};

export default { getUser, saveUser, destroyUser };
