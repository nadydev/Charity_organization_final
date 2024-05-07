function getTotalDonation() {
  // Retrieve input values
  const moneyValue = Number(document.getElementById("money-value").value) || 0;
  const propertySharesValue = Number(document.getElementById("property-shares").value) || 0;
  const bondsValue = Number(document.getElementById("bonds-value").value) || 0;
  const profitValue = Number(document.getElementById("profit-value").value) || 0;
  const gold18Value = Number(document.getElementById("goldFirstKarat").value) || 0;
  const gold21Value = Number(document.getElementById("goldSecondKarat").value) || 0;
  const buildingValue = Number(document.getElementById("building-value").value) || 0;

  // Calculate donation amounts
  const cashAmount = moneyValue * 0.025;
  const propertyAmount = (propertySharesValue + bondsValue + profitValue) * 0.025;
  const goldAmount = (gold18Value * 2259 + gold21Value * 2635) * 0.025;
  const buildingAmount = buildingValue * 0.025;

  // Calculate total donation amount
  const totalDonations = cashAmount + propertyAmount + goldAmount + buildingAmount;

  // Update HTML elements with results
  document.getElementById("money-result").innerHTML = Math.ceil(cashAmount);
  document.getElementById("property-result").innerHTML = Math.ceil(propertyAmount);
  document.getElementById("gold-result").innerHTML = Math.ceil(goldAmount);
  document.getElementById("building-result").innerHTML = Math.ceil(buildingAmount);
  document.getElementById("zakat-total").innerHTML = Math.ceil(totalDonations);
}
