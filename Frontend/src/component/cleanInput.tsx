function cleanInput(str: string) {
  if (!str) return "";

  return str.replace(/["<>']/g, "")
}

export default cleanInput;
