function showMoreSelect(selectedValue) {
  const selectedOption = selectedValue.value;
  const sponsorSelect = document.getElementById('sponorSelect');
  const donorSelect = document.getElementById('donorSelect');
  const volunteerSelect = document.getElementById('volunteerSelect');

  switch(selectedOption) {
    case 'Donor':
      sponsorSelect.classList.add('d-none')
      volunteerSelect.classList.add('d-none');
      donorSelect.classList.remove('d-none');
      break;
    case 'Sponsor':
      donorSelect.classList.add('d-none');
      volunteerSelect.classList.add('d-none');
      sponsorSelect.classList.remove('d-none');
      break;
    case 'Volunteer':
      donorSelect.classList.add('d-none');
      sponsorSelect.classList.add('d-none');
      volunteerSelect.classList.remove('d-none');
      break;
    default:
      donorSelect.classList.add('d-none');
      sponsorSelect.classList.add('d-none');
      volunteerSelect.classList.add('d-none');
  }
}
