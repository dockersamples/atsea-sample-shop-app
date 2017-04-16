export const getJwtToken = () => localStorage.getItem('jwtToken');
export const setJwtToken = (token) => localStorage.setItem('jwtToken', token);
export const removeJwtToken = () => localStorage.removeItem('jwtToken');
